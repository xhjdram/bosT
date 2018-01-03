package com.serviceimpl;
import com.SubareaDao;
import com.domain.BcStaffEntity;
import com.domain.BcSubareaEntity;
import com.domain.PageBean;
import com.service.ISubareaService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaService implements ISubareaService {
    @Autowired
    SubareaDao subareaDao;
    @Override
    public void save(BcSubareaEntity t) {
        subareaDao.addEntity(t);
    }
    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<BcSubareaEntity> findAll() {
        List<BcSubareaEntity> bcSubareaEntities = subareaDao.selectEntityAll();
        return bcSubareaEntities;
    }

    @Override
    public List<BcSubareaEntity> findSubarea() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BcSubareaEntity.class);
        detachedCriteria.add(Restrictions.isNull("bcDecidedzoneByDecidedzoneId"));
        List<BcSubareaEntity> entityByDe = subareaDao.findEntityByDe(detachedCriteria);
        return entityByDe ;
    }

    @Override
    public List<BcSubareaEntity> findSubareaByDecidedzoneId(String id) {
        DetachedCriteria detachedCriteria =DetachedCriteria.forClass(BcSubareaEntity.class);
        detachedCriteria.add(Restrictions.eq("bcDecidedzoneByDecidedzoneId.id",id));
        List<BcSubareaEntity> entityByDe = subareaDao.findEntityByDe(detachedCriteria);
        return entityByDe;
    }

    @Override
    public List<Object> subareaPicture() {
       List<Object> list= subareaDao.subareaPicture();
        return list;
    }
}
