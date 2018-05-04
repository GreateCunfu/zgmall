package com.op.marvel.dc.zhg38.common.source.bean.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 16:24 on 2018/5/2.
 */
public class PageBeanDto<T> implements Serializable {

    private static final long serialVersionUID = -3953772369286066983L;


    /** 总条数 */
    private long total;

    /** 页大小 ,当前页结果*/
    private List<T> rows;


    /** 当前页 */
    private long page;

    /** 总页数 */
    private long records;

    public PageBeanDto() {  }

    public PageBeanDto(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }



    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }
}
