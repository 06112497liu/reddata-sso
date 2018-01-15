/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.result;

/**
 * SSO分页查询基类
 * 
 * @author byron
 * @version $Id: BdssoBasePageResult.java, v 0.1 Sep 26, 2017 4:55:02 PM byron Exp $
 */
public class BdssoBasePageResult extends BdssoBaseResult {

    /** 序列化id */
    private static final long serialVersionUID = -3014728665998576742L;

    /** 页号 */
    private int               pageNum;

    /** 每页条数 */
    private int               pageSize;

    /** 总条数 */
    private int               total;

    /**
     * Getter method for property <tt>pageNum</tt>.
     * 
     * @return property value of pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Setter method for property <tt>pageNum</tt>.
     * 
     * @param pageNum value to be assigned to property pageNum
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     * 
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     * 
     * @param pageSize value to be assigned to property pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>total</tt>.
     * 
     * @return property value of total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Setter method for property <tt>total</tt>.
     * 
     * @param total value to be assigned to property total
     */
    public void setTotal(int total) {
        this.total = total;
    }

}
