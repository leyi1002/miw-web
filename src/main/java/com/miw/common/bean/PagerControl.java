package com.miw.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by J lai on 2017/6/8 0008.
 */
public class PagerControl<T> implements Serializable {

    private static final long serialVersionUID = 4297791392014177523L;
    private List<T> entityList;
    private PageInfo          pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 获取分页列表集合
     *
     */
    public List<T> getEntityList() {
        return entityList;
    }

    /**
     * 放入分页列表集合
     *
     */
    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    @Override
    public String toString() {
        return "PagerControl [entityList=" + entityList + ", pageInfo="
                + pageInfo + "]";
    }

}
