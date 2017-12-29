package com.daoimpl;

import com.dao.IBaseDao;
import com.domain.BcStaffEntity;
import com.domain.PageBean;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    Class<T> entityClass;

    @Resource
    public void mySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Override
    public void addEntity(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void delectEntity(T t) {
        this.getHibernateTemplate().delete(t);

    }

    @Override
    public void alterEntity(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public List<T> selectEntityAll() {
        String simpleName = entityClass.getSimpleName();
        String hql = "from" + " " + simpleName;
        List<T> ts = (List<T>) this.getHibernateTemplate().find(hql);
        return ts;
    }

    @Override
    public T selectEntityById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        int firstIndext = pageBean.getPageSize() * (pageBean.getCurrentPage() - 1);
        int maxI = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> byCriteria = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long aLong = byCriteria.get(0);
        pageBean.setTotal(aLong);
        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        List byCriteria1 = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstIndext, maxI);
        pageBean.setRows(byCriteria1);
    }

    @Override
    public void saveOrUpdate(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    public List<T> findEntityByDe(DetachedCriteria detachedCriteria) {
        List<T> byCriteria = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return byCriteria;
    }
}
