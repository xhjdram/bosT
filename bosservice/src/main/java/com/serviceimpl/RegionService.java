package com.serviceimpl;

import com.RegionDao;
import com.domain.BcRegionEntity;
import com.domain.PageBean;
import com.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionService implements IRegionService {
    @Autowired
    private RegionDao regionDao;
    @Override
    public void saveOrupdate(List<BcRegionEntity> list) {
        for(BcRegionEntity bcRegionEntity : list){
            regionDao.saveOrUpdate(bcRegionEntity);
        }
    }

    @Override
    public void listRegion(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public List<BcRegionEntity> findAll() {
        List<BcRegionEntity> list = regionDao.selectEntityAll();
        return list;
    }
}
