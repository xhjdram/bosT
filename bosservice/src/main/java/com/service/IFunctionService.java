package com.service;

import com.domain.Function;
import com.domain.PageBean;

import java.util.List;

public interface IFunctionService {
    public List<Function> ajaxList();
    void save(Function t);

   void pageQuery(PageBean pageBean);
}
