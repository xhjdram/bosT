package com.service;

import com.domain.BcStaffEntity;
import com.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface IStaffService {
    void addEntity(BcStaffEntity t);

    void pageQuery(PageBean pageBean);

    void deleteStaff(String[] split);

    BcStaffEntity getStaffById(String id);

    void updataStaff(BcStaffEntity bcStaffEntity);

    List<BcStaffEntity> findStaffNoDelete();
}
