package com.serviceimpl;

import com.StaffDao;
import com.domain.BcStaffEntity;
import com.domain.PageBean;
import com.service.IStaffService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    StaffDao staffDao;

    @Override
    public void addEntity(BcStaffEntity t) {
        staffDao.addEntity(t);
    }

    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public void deleteStaff(String[] split) {
        for (String id : split) {
            staffDao.update("deleteStaff", id);
        }

    }

    @Override
    public BcStaffEntity getStaffById(String id) {

        return staffDao.selectEntityById(id);
    }

    @Override
    public void updataStaff(BcStaffEntity bcStaffEntity) {
        staffDao.alterEntity(bcStaffEntity);
    }

    @Override
    public List<BcStaffEntity> findStaffNoDelete() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BcStaffEntity.class);
        detachedCriteria.add(Restrictions.eq("deltag","0"));
        List<BcStaffEntity> list = staffDao.findEntityByDe(detachedCriteria);
        return list;
    }
}
