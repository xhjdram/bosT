package com;

import com.daoimpl.BaseDaoImpl;
import com.domain.BcStaffEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public class StaffDao extends BaseDaoImpl<BcStaffEntity> {
    public int update(String namedQuery, Object... objects) {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        Query namedQuery1 = currentSession.getNamedQuery(namedQuery);
        int i = 0;
        for (Object id : objects) {
            namedQuery1.setParameter(i, id);
            i++;
        }
        try {
            namedQuery1.executeUpdate();
            return 1;
        } catch (Exception e) {

        }
        return 0;
    }
}
