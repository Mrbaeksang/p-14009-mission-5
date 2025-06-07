package com.back.standard.dto;

import java.util.List;

public class Page<T> {
    private final int totalCount;
    private final int pageNo;
    private final int pageSize;
    private final List<T> content;

    public Page(int totalCount, int pageNo, int pageSize, List<T> content) {
        this.totalCount = totalCount;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.content = content;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}
