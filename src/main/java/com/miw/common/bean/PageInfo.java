package com.miw.common.bean;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类是公共翻页
 *
 * @author Sean
 */
public class PageInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9184978320374555409L;
    /**
     * 一共条数
     */
    private int totalCounts = 0;
    /**
     * 拥有页数
     */
    private int allPage = 0;
    /**
     * 当前页数
     */
    private int page = 1;

    private long startTime = 0L;
    private long endTime = 0L;
    /**
     * 是否有上一页
     */
    private Integer isPrev = 0;


    private String orderField;
    private String orderDirection;


    private String orderBySql;

    private int prev;

    private int next;

    private int showPageSize;

    public int getShowPageSize() {
        return showPageSize;
    }

    public void setShowPageSize(int showPageSize) {
        this.showPageSize = showPageSize;
    }

    //上一页
    public Integer next() {
        if (page > 0) {
            next = page - 1;
            return next;
        } else {
            next = 1;
            return next;
        }
    }

    //下一页
    public Integer previous() {
        if (page < allPage) {
            prev = page + 1;
            return prev;
        } else {
            prev = allPage;
            return prev;
        }
    }

    public int getNext() {
        if (page > 0) {
            this.next = page - 1;
        } else {
            this.next = 1;
        }
        return this.next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        if (page < allPage) {
            this.prev = page + 1;
        } else {
            this.prev = allPage;
        }
        return this.prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public String getOrderBySql() {
        return orderBySql;
    }

    public void setOrderBySql(String orderBySql) {
        this.orderBySql = orderBySql;
    }

    public Integer getIsPrev() {
        return isPrev;
    }

    public void setIsPrev(Integer isPrev) {
        this.isPrev = isPrev;
    }

    public Integer getIsNext() {
        return isNext;
    }

    public void setIsNext(Integer isNext) {
        this.isNext = isNext;
    }

    /**
     * 是否有下一页
     */
    private Integer isNext = 0;
    /**
     * 当前翻页列表
     */
    private List<Integer> listPages = new ArrayList<Integer>();

    public List<Integer> getListPages() {
        return this.listPages;
    }

    public void startTime() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * 停止计时
     */
    public void endTime() {
        this.endTime = System.currentTimeMillis();
        setUseTime(endTime - startTime);
    }

    @Override
    public String toString() {
        return "PageInfo [allPage=" + allPage + ", endTime=" + endTime
                + ", isNext=" + isNext + ", isPrev=" + isPrev + ", listPages="
                + listPages + ", page=" + page + ", pagesize=" + pagesize
                + ", startIndex=" + startIndex + ", startTime=" + startTime
                + ", totalCounts=" + totalCounts + ", useTime=" + useTime + "]";
    }

    /**
     * 当前页返回条数
     */
    private int pagesize = 10;
    /**
     * 耗时
     */
    private String useTime = "0";

    private int startIndex = 0;

    public PageInfo() {
        this.startTime = System.currentTimeMillis();
    }

    public PageInfo(int page, int pagesize) {
        super();
        if (page < 1)
            page = 1;
        this.page = page;
        this.pagesize = pagesize;
        this.startIndex = (page - 1) * pagesize;
        this.startTime = System.currentTimeMillis();
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        DecimalFormat df = new DecimalFormat("0");
        this.useTime = df.format((Float.parseFloat(useTime + "") / 1000));
        df = null;
    }

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
        if (showPageSize <= 0) {
            showPageSize = 10;
        }
        executePage(showPageSize);
    }

    public void executePage(int showPageSize) {
        // 分页算法
        if (totalCounts > 0) {
            allPage = totalCounts / pagesize;
            if (totalCounts % pagesize != 0)
                allPage++;
        }
        int startX = 1;
        // 计算开始位置
        // 上一页
        if (page > 1 && page <= allPage)
            isPrev = page - 1;
        if (showPageSize == 10) {
            //若总页码大于10页,且页码大于第4页小于倒数第4页,则索引-4
            if (page > 4 && page < allPage -4){
                startX = page - 4;
            }else if(page > 4 && page >= allPage-4  && allPage > 10){
                //若页码总数大于10页,且页码在倒数5页内
                startX = allPage -9;
            }
        } else {
            int pageRange = showPageSize - 1;
            if (allPage - page >= 0) {
                if (page > allPage - pageRange && page > pageRange) {
                    startX = allPage - pageRange;
                } else if (page <= allPage - pageRange && page > pageRange) {
                    startX = page - 1;
                } else if (page <= pageRange) {
                    startX = 1;
                }
            }
        }

        for (int i = startX; i < startX + showPageSize && i <= allPage; i++)
            listPages.add(i);
        // 下一页
        if (page < allPage)
            isNext = page + 1;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        //    if (page < 20) {
        if (page < 1)
            page = 1;
        this.page = page;
        this.startIndex = (page - 1) * pagesize;
        //    }
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
        if (showPageSize <= 0) {
            showPageSize = 10;
        }
        this.startIndex = (page - 1) * pagesize;
        executePage(showPageSize);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setListPages(List<Integer> listPages) {
        this.listPages = listPages;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }


    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public static void main(String[] args) {
        PageInfo pageInfo = new PageInfo(1, 10);
        pageInfo.setTotalCounts(60);
        List<Integer> list = pageInfo.getListPages();
        for (Integer integer : list) {
            System.out.println(integer);
        }

        try {
            Thread.sleep(2013);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pageInfo.endTime();

        System.out.println(pageInfo.getIsNext());
        System.out.println(pageInfo.getIsPrev());

        System.out.println(pageInfo.getUseTime());
    }
}
