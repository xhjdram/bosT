package com;

import com.daoimpl.BaseDaoImpl;
import com.domain.BcSubareaEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class SubareaDao extends BaseDaoImpl<BcSubareaEntity> {
    //查询每个区域有多少个分区
    public List<Object> subareaPicture() {
        String hql = "SELECT r.province ,count(*) FROM BcSubareaEntity s LEFT OUTER JOIN s.bcRegionByRegionId r Group BY r.province";
        List<Object> objects = (List<Object>) this.getHibernateTemplate().find(hql);
        return objects;
    }
}
