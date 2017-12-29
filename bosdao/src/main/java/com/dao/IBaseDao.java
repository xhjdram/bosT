package com.dao;

import com.domain.BcStaffEntity;
import com.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    void addEntity(T t);

    void delectEntity(T t);

    void alterEntity(T t);

    List<T> selectEntityAll();

    T selectEntityById(Serializable id);

    void pageQuery(PageBean pageBean);

    void saveOrUpdate(T t);

    List<T> findEntityByDe(DetachedCriteria detachedCriteria);
}
