package com.op.marvel.dc.zhg38.common.source.bean.vo;

import com.op.marvel.dc.zhg38.source.utils.JsonUtil;

import java.io.Serializable;

/**
 * @version 3.0
 * @Author :History.GreatMan.Mao
 * @Description:
 * @Date Created in 21:56 on 2018/5/2.
 */
public class ResultInfo implements Serializable {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;


    public static ResultInfo build(Integer status, String msg, Object data) {
        return new ResultInfo(status, msg, data);
    }
    public static ResultInfo build(Integer status, String msg) {
        return new ResultInfo(status, msg, null);
    }
    public static ResultInfo ok(Object data) {
        return new ResultInfo(data);
    }

    public static ResultInfo ok() {
        return new ResultInfo(null);
    }

    public ResultInfo() {

    }



    public ResultInfo(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultInfo(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ResultInfo format(String json) {
        try {
            return JsonUtil.json2Object(json,ResultInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
