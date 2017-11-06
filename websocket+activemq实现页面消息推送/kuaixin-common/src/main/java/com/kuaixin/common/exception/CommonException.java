//~ Formatted by Jindent --- http://www.jindent.com


/**
 * @Title: YnCenterException.java
 * @author kay won
 * @date 2015/04/23 
 * @Copyright:  www.yineng.com.cn Inc. All rights reserved.
 * @version v1.0
 */

package com.kuaixin.common.exception;

//~--- non-JDK imports --------------------------------------------------------



/**
 * @ClassName CommonException
 * @Description 服务中心自定义异常
 * @author lishuai
 * @Date 2017/04/21
 */
public class CommonException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 883368829481007561L;

    /**
     * statusMessage
     */
    private StatusMessage statusMessage;

    /**
     * msg
     */
    private String msg;

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     * @param statusMessage 异常枚举
     * @param msg 异常信息
     */
    public CommonException(StatusMessage statusMessage, String msg) {
        this(statusMessage, msg, null);
    }

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     * @param statusMessage
     * @param msg
     * @param cause
     */
    public CommonException(StatusMessage statusMessage, String msg, Throwable cause) {
        super( notNull(statusMessage)+"【【"+msg+"】】",cause);
        this.statusMessage = statusMessage;
        this.msg           = msg;
    }

    public CommonException(String message, Object o) {
        super( message,null);

    }


    /**
     * @Title: getStatusCode
     * @Description: 方法描述
     * @return
     */
    public String getStatusCode() {
        return statusMessage.getCode();
    }



    /**
     * @Title: getStatusMessage
     * @Description: 方法描述
     * @return
     */
    public String getStatusMessage() {
        return  statusMessage.getMessage() + "【【" + msg + "】】";
    }

    /**
     * @Title: notNull
     * @Description: 方法描述
     * @param statusMessage
     * @return
     */
    private static String notNull(StatusMessage statusMessage) {
        if (null == statusMessage) {
            throw new IllegalArgumentException("statusMessage can't be null");
        }

        return statusMessage.getMessage();
    }

    /**
     * @Title: statusMessage
     * @Description: 方法描述
     * @return
     */
    public StatusMessage statusMessage() {
        return statusMessage;
    }



    public CommonException(String message) {
        super(message);
    }


}
