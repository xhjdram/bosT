package com.service;

import com.domain.BcSubareaEntity;
import com.domain.PageBean;

import java.util.List;

public interface ISubareaService {
    void save(BcSubareaEntity t);

    void pageQuery(PageBean pageBean);

    List<BcSubareaEntity> findAll();

    List<BcSubareaEntity> findSubarea();
    List<BcSubareaEntity> findSubareaByDecidedzoneId(String id);
}
