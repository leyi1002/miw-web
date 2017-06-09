package com.miw.dao.impl;

import com.miw.common.bean.PageInfo;
import com.miw.common.bean.PagerControl;
import com.miw.common.bean.QueryPageDTO;
import com.miw.common.exception.ServiceException;
import com.miw.dao.IDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by J lai on 2017/6/5 0005.
 */
public abstract class BaseDao<M extends Serializable> extends SqlSessionDaoSupport implements IDao<M> {

    protected static final String insertSelective = ".insertSelective";
    protected static final String updateSelective = ".updateByPrimaryKeySelective";
    protected static final String selectByPrimaryKey = ".selectByPrimaryKey";
    protected static final String selectByPrimaryKeys = ".selectByPrimaryKeys";
    protected static final String getListByEntityAndPageInfo = ".getListByEntityAndPageInfo";
    protected static final String getTotalByEntity = ".getTotalByEntity";
    protected static final String deleteByPrimaryKey = ".deleteByPrimaryKey";

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

    @Override
    public int insert(M entity) {
        try {
            return getSqlSession().insert(getMapperNameSpace() + insertSelective, entity);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".insert: {}", entity == null ? "" : entity.toString(), e);
        }
    }

    @Override
    public int update(M entity) {
        try {
            return getSqlSession().update(getMapperNameSpace() + updateSelective, entity);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".update: {}", entity == null ? "" : entity.toString(), e);
        }
    }

    @Override
    public int delete(Integer pk) {
        try {
            return getSqlSession().delete(getMapperNameSpace() + deleteByPrimaryKey, pk);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".delete: {}", pk.toString(), e);
        }
    }


    @Override
    public M getEntityById(Integer pk) {
        try {
            return getSqlSession().selectOne(getMapperNameSpace() + selectByPrimaryKey, pk);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getEntityById: {}", pk.toString(), e);
        }
    }

    @Override
    public M getEntityByObj(M entity) {
        try {
            return getEntityByObj(entity, null);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getEntityByObj: {}", entity == null ? "" : entity.toString(), e);
        }
    }

    @Override
    public M getEntityByObj(M entity, String whereSql) {
        try {
            return getSqlSession().selectOne(getMapperNameSpace() + getListByEntityAndPageInfo, QueryPageDTO.getQuery(entity, null, whereSql));
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getEntityByObj: {},{}", new String[]{entity == null ? "" : entity.toString(), whereSql}, e);
        }
    }

    @Override
    public int getCountByObj(M entity) {
        try {
            return getCountByObj(entity, null);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getCountByObj: {}", entity == null ? "" : entity.toString(), e);
        }
    }

    @Override
    public int getCountByObj(M entity, String whereSql) {
        try {
            Object selectOne = getSqlSession().selectOne(getMapperNameSpace() + getTotalByEntity, QueryPageDTO.getQuery(entity, null, whereSql, null));
            return (Integer) selectOne;
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getCountByObj: {},{}", new String[]{entity == null ? "" : entity.toString(), whereSql}, e);
        }
    }

    @Override
    public PagerControl<M> getPagerByObj(M entity, PageInfo pageInfo, String whereSql, String orderBySql) {
        PagerControl<M> pagerControl = new PagerControl<M>();
        pageInfo.startTime();
        List<M> list = new ArrayList<M>();
        int total;
        try {
            total = getCountByObj(entity, whereSql);
            if (total > 0) {
                list = getListByObj(entity, pageInfo, whereSql, orderBySql);
            }
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getPagerByObj-" + e.getMessage() + ": {},{},{},{}", new String[]{entity == null ? "" : entity.toString(),
                    pageInfo.toString(), whereSql, orderBySql}, e);
        }

        pageInfo.endTime();
        pageInfo.setTotalCounts(total);
        if (list != null) {
            pagerControl.setEntityList(list);
        }
        pagerControl.setPageInfo(pageInfo);
        return pagerControl;
    }

    @Override
    public PagerControl<M> getPagerByObj(M entity, PageInfo pageInfo, String whereSql) {
        try {
            return getPagerByObj(entity, pageInfo, whereSql, null);
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getPagerByObj-" + e.getMessage() + ": {},{},{},{}", new String[]{entity == null ? "" : entity.toString(),
                    pageInfo.toString(), whereSql}, e);
        }
    }

    @Override
    public List<M> getAllEntityObj() {
        return getListByObj(null, null);
    }

    @Override
    public List<M> getListByObj(M entity) {
        return getListByObj(entity, null, null, null);
    }

    @Override
    public List<M> getListByObj(M entity, String whereSql) {
        return getListByObj(entity, null, whereSql, null);
    }

    @Override
    public List<M> getListByObj(M entity, String whereSql, String orderBySql) {
        return getListByObj(entity, null, whereSql, orderBySql);
    }

    @Override
    public List<M> getListByObj(M entity, PageInfo pageInfo, String whereSql) {
        return getListByObj(entity, pageInfo, whereSql, null);
    }

    @Override
    public List<M> getListByObj(M entity, PageInfo pageInfo, String whereSql, String orderBySql) {
        try {
            return getSqlSession().selectList(getMapperNameSpace() + getListByEntityAndPageInfo,
                    QueryPageDTO.getQuery(entity, pageInfo, whereSql,
                            pageInfo != null && !StringUtils.isEmpty(pageInfo.getOrderField())
                                    ? getOrderBySql(pageInfo) : orderBySql
                    )
            );
        } catch (Exception e) {
            throw new ServiceException(getMapperNameSpace() + ".getListByObj: {},{},{},{}",
                    new String[]{entity == null ? "" : entity.toString(),
                            (pageInfo != null) ? pageInfo.toString() : "", whereSql, orderBySql}, e
            );
        }
    }

    public String getOrderBySql(PageInfo pageInfo) {
        if (pageInfo == null) return null;
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(pageInfo.getOrderField())) {
            sb.append("order by ")
                    .append(pageInfo.getOrderField())
                    .append(" ")
                    .append("desc".equals(pageInfo.getOrderDirection()) ? "desc" : "asc");
        }
        return sb.toString();
    }
}
