package com.maven.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-16 21:49
 */
@Data
public class PaginationDto<T> {
    private static final int PAGE_ALIAS_NUM = 3;
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPages;

    public void setPagination(Integer totalCount, Integer page, Integer size, Integer totalPages) {
        this.totalPages = totalPages;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= PAGE_ALIAS_NUM; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= this.totalPages) {
                pages.add(page + i);
            }
        }
        //是否展示上一页和下一页
        showPrevious = page != 1;
        showNext = !page.equals(this.totalPages);
        //是否展示第一页和最后一页
        showFirstPage = !pages.contains(1);
        showEndPage = !pages.contains(this.totalPages);
    }

    public void setPagination(int totalCount, Integer page, Integer size) {
        if (page < 1)
        {
            page = 1;
        }
        if(totalCount % size == 0)
        {
            this.totalPages = totalCount / size;
        }else{
            this.totalPages = totalCount / size + 1;
        }
        if (page > this.totalPages)
        {
            page = this.totalPages;
        }
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= PAGE_ALIAS_NUM; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= this.totalPages) {
                pages.add(page + i);
            }
        }
        //是否展示上一页和下一页
        showPrevious = page != 1;
        showNext = !page.equals(this.totalPages);
        //是否展示第一页和最后一页
        showFirstPage = !pages.contains(1);
        showEndPage = !pages.contains(this.totalPages);
    }
}
