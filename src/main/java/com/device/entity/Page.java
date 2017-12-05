package com.device.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 */
public class Page<T> implements Serializable {


    private static final long serialVersionUID = 8577912091855038071L;
    /**
     * 默认每页记录数
     */
    private static int DEFAULT_LIMIT = 15;


    /**
     * 取数据偏移
     * Rest Api使用
     */
    private long offset = 0;

    /**
     * 总数据量
     */
    private long total = 0;


    /**
     * 取数据数量
     * Rest Api使用
     */
    private int limit = DEFAULT_LIMIT;

    /**
     * 查询数据结果
     */
    private List<T> result = new ArrayList<T>();

    /**
     * 查询条件存放处
     */
    private Map<String, Object> filter = new HashMap<String, Object>();

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 当前页
     */
    private long page = 1;

    /**
     * 等级
     */
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getPage() {
        return (offset / limit) + 1;
    }

    public void setPage(long page) {
        offset = (page - 1) * limit;
        this.page = page;
    }

    public void setPage(Long page) {
        setPage(page != null ? page.longValue() : 1);
    }

    public long getTotalPage() {
        if (total < 0) {
            return -1;
        }
        long count = total / limit;
        if (total % limit != 0) {
            count++;
        }
        if (count == 0) {
            count = 1;
        }
        this.totalPage = count;
        return count;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setOffset(Long offset) {
        setOffset(offset != null ? offset.longValue() : 0);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        getTotalPage();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit <= 0) {
            limit = DEFAULT_LIMIT;
        }
        this.limit = limit;
    }

    public void setLimit(Integer limit) {
        setLimit(limit != null ? limit.intValue() : DEFAULT_LIMIT);
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }


    private String keyWords;

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getKeyWords() {
        return keyWords;
    }

    private Integer orderAssignStatus;

    public Integer getOrderAssignStatus() {
        return orderAssignStatus;
    }

    public void setOrderAssignStatus(Integer orderAssignStatus) {
        this.orderAssignStatus = orderAssignStatus;
    }
}