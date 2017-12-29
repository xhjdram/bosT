package com.service;

import com.domain.BcRegionEntity;
import com.domain.PageBean;

import java.util.List;

public interface IRegionService {
    void saveOrupdate(List<BcRegionEntity> list);
    void listRegion(PageBean pageBean);
     List<BcRegionEntity>   findAll();
}
