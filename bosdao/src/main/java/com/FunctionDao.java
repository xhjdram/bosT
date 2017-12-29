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
}
