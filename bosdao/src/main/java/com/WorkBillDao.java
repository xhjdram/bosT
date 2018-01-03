package com;

import com.daoimpl.BaseDaoImpl;
import com.domain.QpWorkbillEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class WorkBillDao extends BaseDaoImpl<QpWorkbillEntity> {
    public List<QpWorkbillEntity> findNewWorkbills() {
        String hql ="FROM QpWorkbillEntity WHERE type='new' ";
        List<QpWorkbillEntity> objects = (List<QpWorkbillEntity>) this.getHibernateTemplate().find(hql);
        return objects;
    }
}
