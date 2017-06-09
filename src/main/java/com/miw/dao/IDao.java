package com.miw.dao;

import com.miw.common.bean.PageInfo;
import com.miw.common.bean.PagerControl;

import java.util.List;

/**
 * Created by J lai on 2017/6/8 0008.
 */
public interface IDao<M> {

    int insert(M entity);

    int update(M entity);

    int delete(Integer pk);

    M getEntityById(Integer pk);

    M getEntityByObj(M entity);

    M getEntityByObj(M entity, String whereSql);

    int getCountByObj(M entity);

    int getCountByObj(M entity, String whereSql);

    PagerControl<M> getPagerByObj(M entity, PageInfo pageInfo, String whereSql, String orderBySql);

    PagerControl<M> getPagerByObj(M entity, PageInfo pageInfo, String whereSql);

    List<M> getAllEntityObj();

    List<M> getListByObj(M entity);

    List<M> getListByObj(M entity, String whereSql);

    List<M> getListByObj(M entity, String whereSql, String orderBySql);

    List<M> getListByObj(M entity, PageInfo pageInfo, String whereSql);

    List<M> getListByObj(M entity, PageInfo pageInfo, String whereSql, String orderBySql);

}
