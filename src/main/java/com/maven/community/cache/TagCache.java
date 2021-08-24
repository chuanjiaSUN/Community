package com.maven.community.cache;

import com.maven.community.dto.TagDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-24 21:23
 */
public class TagCache {
    public static List<TagDto> get()
    {
        ArrayList<TagDto> tagDtos = new ArrayList<>();
        TagDto program = new TagDto();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","java","c++","c","python","node","html","golang","object-c","typescript","shell","swift"));
        tagDtos.add(program);
        TagDto framework = new TagDto();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","SpringBoot","SpringMvc","SpringCloud","netflix","ruby","hadoop","koa","express","struts","django"));
        tagDtos.add(framework);
        TagDto server = new TagDto();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","hystrix","docker","apache","ubuntu","centos","tomcat","unix","windows-server"));
        tagDtos.add(server);
        TagDto db = new TagDto();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis","mongodb","sql","oracle","nosql","sqlserver","sqlite"));
        tagDtos.add(db);
        TagDto tool = new TagDto();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","vim","svn","atom","eclipse","idea","maven","visual-studio-code"));
        tagDtos.add(tool);
        return tagDtos;
    }

    public static String filterInvalid(String tags)
    {
        String[] split = StringUtils.split(tags, ",");
        List<TagDto> tagDtos = get();
        List<String> tagList = tagDtos.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalidTag = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining());
        return invalidTag;
    }
}
