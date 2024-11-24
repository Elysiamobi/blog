package com.yangcheng.initial.common;

import lombok.Data;

@Data
public class PageQueryRequest {

    /**
     * 分页数
     */
    private Integer pageSize;

    /**
     * 当前页
     */
    private Integer currentPage;
}
