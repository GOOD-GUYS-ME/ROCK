package com.rock.miaosha.common.wraper;

import java.io.Serializable;
import java.util.List;
/**
*@author rock
*@Date 2019/9/17 23:14
*@param 
*@return 
*
*/
public class Wrapper<T> implements Serializable {

    private static final long serialVersionUID = -8487183569094095497L;
    private int code;
    private String msg;
    private T t;
    private List<T> list;
    private int totalCount;
    private int currentPage;
    private int totalPage;
    private int pageSize;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", t=" + t +
                ", list=" + list +
                '}';
    }
}
