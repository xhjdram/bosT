package com;

import com.daoimpl.BaseDaoImpl;
import com.domain.QpWorkbillEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public class WorkBillDao extends BaseDaoImpl<QpWorkbillEntity> {
}
