/**  
 * BBD Service Inc
 * All Rights Reserved @2017
 */
package com.bbd.bdsso.common.service.facade.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * SSO扩展结果
 * 
 * @author byron
 * @version $Id: SsoEArrayList<E>xVO.java, v 0.1 Oct 19, 2017 4:29:35 PM byron Exp $
 */
public class SsoExVO<E> implements Serializable {

    /** 序列化id */
    private static final long serialVersionUID = -2051905128798448479L;

    /** 用户id */
    private String            id;

    /** 用户名 */
    private String            name;

    /** 结果列表 */
    private ArrayList<E>      list;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>list</tt>.
     * 
     * @return property value of list
     */
    public ArrayList<E> getList() {
        return list;
    }

    /**
     * Setter method for property <tt>list</tt>.
     * 
     * @param list value to be assigned to property list
     */
    public void setList(ArrayList<E> list) {
        this.list = list;
    }

}
