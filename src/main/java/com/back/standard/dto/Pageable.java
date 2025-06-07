package com.back.standard.dto;

public class Pageable {
    private final int pageNo;
    private final int pageSize;

    public Pageable(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getSkipCount() {
        return (long) (pageNo - 1) * pageSize;
    }
}
