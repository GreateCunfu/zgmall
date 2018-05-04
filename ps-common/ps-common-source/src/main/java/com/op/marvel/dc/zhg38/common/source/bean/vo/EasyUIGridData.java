package com.op.marvel.dc.zhg38.common.source.bean.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 18:53 on 2018/5/2.
 */
public class EasyUIGridData implements Serializable {;

    private Long total;
    private List<?> rows;

    public EasyUIGridData() {

    }

    public EasyUIGridData(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

