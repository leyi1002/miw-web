package com.miw.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by J lai on 2017/6/8 0008.
 */
public final class QueryPageDTO<E extends Serializable> {
    private E entity;
    private List<?> entities;
    private PageInfo pageInfo;
    private String whereSql;
    private String orderBy;


    private QueryPageDTO(E entity, PageInfo pageInfo, String whereSql, String orderBy) {
        this.entity = entity;
        this.pageInfo = pageInfo;
        this.whereSql = whereSql;
        this.orderBy = orderBy;
    }

    private QueryPageDTO(List<?> entities, PageInfo pageInfo, String whereSql, String orderBy) {
        this.entities = entities;
        this.pageInfo = pageInfo;
        this.whereSql = whereSql;
        this.orderBy = orderBy;
    }

    public static <E extends Serializable> QueryPageDTO getQuery(E entity) {
        return getQuery(entity, null, null, null);
    }

    public static <E extends Serializable> QueryPageDTO getQuery(E entity, PageInfo pageInfo) {
        return getQuery(entity, pageInfo, null, null);
    }

    public static <E extends Serializable> QueryPageDTO getQuery(E entity, PageInfo pageInfo, String whereSql) {
        return getQuery(entity, pageInfo, whereSql, null);
    }

    public static <E extends Serializable> QueryPageDTO getQuery(E entity, PageInfo pageInfo, String whereSql, String orderBy) {
        return new QueryPageDTO(entity, pageInfo, whereSql, orderBy);
    }

    public static <E extends Serializable> QueryPageDTO getQuery(List<?> entities) {
        return new QueryPageDTO(entities, null, null, null);
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public List<?> getEntities() {
        return entities;
    }

    public void setEntities(List<?> entities) {
        this.entities = entities;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getWhereSql() {
        return whereSql;
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
