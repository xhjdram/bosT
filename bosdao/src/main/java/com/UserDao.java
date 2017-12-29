package com;
import com.daoimpl.BaseDaoImpl;
import com.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class UserDao extends BaseDaoImpl<User> {
    public User getUserByPassWordAndUserName(String name, String passWord) {
        String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, name, passWord);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;

    }
    public int update(String namedQuery, Object ... objects){
        SessionFactory sessionFactory = this.getSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();
        Query namedQuery1 = currentSession.getNamedQuery(namedQuery);
        int i=0;
        for(Object id : objects){
            namedQuery1.setParameter(i,id);
            i++;
        }try {
            namedQuery1.executeUpdate();
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    public User findUserByUserName(String username) {
        String hql = "FROM User u WHERE u.username = ? ";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql,username);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
