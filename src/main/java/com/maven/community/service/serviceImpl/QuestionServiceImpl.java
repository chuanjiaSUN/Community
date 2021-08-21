package com.maven.community.service.serviceImpl;

import com.maven.community.dto.PaginationDto;
import com.maven.community.dto.QuestionDto;
import com.maven.community.exception.CustomizeErrorCode;
import com.maven.community.exception.CustomizeException;
import com.maven.community.mapper.QuestionExtMapper;
import com.maven.community.mapper.QuestionMapper;
import com.maven.community.pojo.Question;
import com.maven.community.pojo.QuestionExample;
import com.maven.community.pojo.User;
import com.maven.community.service.QuestionService;
import com.maven.community.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-15 22:32
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Override
    public void createQuestion(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public List<Question> searchList() {
        return questionMapper.selectByExample(new QuestionExample());
    }

    @Override
    public List<QuestionDto> getQuestionDtoList() {
        List<Question> questions = questionMapper.selectByExample(new QuestionExample());
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questions)
        {
           User user =  userService.findById(question.getCreator());
           questionDtoList.add(new QuestionDto(question, user));
        }
        return questionDtoList;
    }

    /**显示首页分页*/
    @Override
    public PaginationDto listPage(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount = selectCount();
        return setPagination(paginationDto, totalCount, page, size);
    }

    @Override
    public Integer selectCount() {
        return (int)questionMapper.countByExample(new QuestionExample());
    }

    /**显示单个user分页*/
    @Override
    public PaginationDto listUserQuestions(Long id, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        List<Question> userQuestions = selectById(id);
        paginationDto.setPagination(userQuestions.size(), page, size);
        if (page < 1)
        {
            page = 1;
        }
        if (page > paginationDto.getTotalPages())
        {
            page = paginationDto.getTotalPages();
        }
        Integer offSet = size * (page - 1);
        /*List<Question> questionList = questionMapper.listPageById(id, offSet, size);*/
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample, new RowBounds(offSet, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        User user = userService.findById(id);
        for (Question question : questionList)
        {
            questionDtoList.add(new QuestionDto(question, user));
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    @Override
    public List<Question> selectById(Long id) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        return questionMapper.selectByExample(questionExample);
    }

    @Override
    public QuestionDto getById(Long id) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andIdEqualTo(id);
        /*Question question = questionMapper.getById(id);*/
        List<Question> questionList = questionMapper.selectByExample(questionExample);
        if (questionList.size() == 0)
        {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        Question question =  questionList.get(0);
        Long creator = question.getCreator();
        User user = userService.findById(creator);
        return new QuestionDto(question, user);
    }

    @Override
    public void createOrUpdate(Question question) {
        if (question.getId() == null)
        {
            //创建
            /*questionMapper.createQuestion(question);*/
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else{
            //更新
            /*questionMapper.update(question);*/
            Question updateQuestion = new Question();
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            int update = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if (update != 1)
            {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    @Override
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    @Override
    public Question selectByPrimaryKey(Long parentId) {
        return questionMapper.selectByPrimaryKey(parentId);
    }

    @Override
    public void incCommentCount(Question question) {
        questionExtMapper.incComment(question);
    }

    public PaginationDto setPagination(PaginationDto paginationDto, int totalCount, Integer page, Integer size)
    {
        paginationDto.setPagination(totalCount, page, size);
        if (page < 1)
        {
            page = 1;
        }
        if (page > paginationDto.getTotalPages())
        {
            page = paginationDto.getTotalPages();
        }
        Integer offSet = size * (page - 1);
        /*List<Question> questionList = questionMapper.listPage(offSet, size);*/
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offSet, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList)
        {
            User user = userService.findById(question.getCreator());
            questionDtoList.add(new QuestionDto(question, user));
        }
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }




}
