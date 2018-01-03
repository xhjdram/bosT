package com;
import com.daoimpl.BaseDaoImpl;
import com.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDao extends BaseDaoImpl<Function> {
    @Override
    public List<Function> selectEntityAll(){
        String hql =" FROM Function WHERE parentFunction is null ";
        List<Function> objects = (List<Function>) this.getHibernateTemplate().find(hql);
        return objects;
    }

    public List<Function> findFunctonsByUserId(String id) {
        String hql ="SELECT DISTINCT f FROM Function f JOIN f.roles r JOIN r.users u WHERE u" +
                ".id=? ";
        List<Function> objects = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return objects;
    }

    public List<Function> generateMenuByUserId(String id) {
        String hql ="SELECT DISTINCT f FROM Function f JOIN f.roles r JOIN r.users u WHERE u" +
                ".id=? AND f.generatemenu='1'";
        List<Function> objects = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return objects;
    }
}
