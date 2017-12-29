package com.domain;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class PageBean {
    Long total;//总条数
    List rows;//结果集
    DetachedCriteria detachedCriteria;//离线查询对象
    Integer currentPage;//当前页
    Integer pageSize;//每页显示条数


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List list) {
        this.rows = list;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
