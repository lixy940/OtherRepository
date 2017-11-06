package com.kuaixin.common.utils;
/*******************************************************************************
 * Copyright (c) 2005, 2014 www.yineng.com
 * 
 * <li>文件名称: JsonBean.java</li>
 * <li>文件描述: </li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
 * <li>完成日期：2014-10-23</li>
 * <li>最后修改: YINENG</li>
 * <li>@version 1.0</li>
 * <li>@author lishuai</li>
 *******************************************************************************/


// TODO: Auto-generated Javadoc

import java.io.Serializable;

/**
 * 浏览器与WEB服务器异步交互的消息体.
 *
 * @author yangziqian
 * @ClassName JsonBean
 * @Description All Module
 * @Date 2014/10/13
 */
public class JsonBean<T> implements Serializable{
    
    /** 异常提示信息. */
    private String message="操作成功";
    
    /** 状态. */
    private String status="0";
    
    /** 结果对象. */
    private T result;

    /**
     * Instantiates a new json bean.
     */
    public JsonBean(){
    }

    /**
     * Instantiates a new json bean.
     *
     * @param result the result
     */
    public JsonBean(T result){
        this.result = result;
    }

    /**
     * Instantiates a new json bean.
     *
     * @param message the message
     * @param status the status
     */
    public JsonBean(String message, String status){
        this.message = message;
        this.status = status;
    }

    /**
     * Instantiates a new json bean.
     *
     * @param message the message
     * @param status the status
     * @param result the result
     */
    public JsonBean(String message, String status, T result){
        this.message = message;
        this.status = status;
        this.result = result;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the result.
     *
     * @return the result
     */
    public Object getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result the new result
     */
    public void setResult(T result) {
        this.result = result;
    }
}

