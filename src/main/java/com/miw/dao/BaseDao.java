package com.miw.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by J lai on 2017/6/5 0005.
 */
public abstract class BaseDao extends SqlSessionDaoSupport {

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 对应 Mapper NameSpace名称 做匹配
     *
     * @return
     */
    public String getMapperNameSpace() {
        return getClass().getInterfaces()[0].getName();
    }

}
