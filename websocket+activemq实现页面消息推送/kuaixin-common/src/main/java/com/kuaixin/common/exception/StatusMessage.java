//~ Formatted by Jindent --- http://www.jindent.com


/**
 * @Title: StatusMessage.java
 * @author kay won
 * @date 2015/04/23
 * @Copyright:  www.yineng.com.cn Inc. All rights reserved.
 * @version v1.0
 */

package com.kuaixin.common.exception;

//~--- JDK imports ------------------------------------------------------------

import java.io.Serializable;

/**
 * Enum description 异常枚举
 *
 */
public enum StatusMessage implements Serializable {
    PASSWORD_ERROR("1","您输入的密码和登录账号不匹配，请重新输入！"),NOT_ALLOWED_NULL("2","不允许为空"),AlREADY_EXISTS("3","已存在！"),
    PARAMETER_NOT_DEFICIENCY("4","参数不正确"),OBJECT_NOT_EXIST("5","对象不存在");
    /**
     * code 异常编码
     */
    private String code;

    /**
     * message  异常消息
     */
    private String message;

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     * @param code
     * @param message
     */
    StatusMessage(String code, String message) {
        this.code    = code;
        this.message = message;
    }

    /**
     * @Title: getCode
     * @Description: 方法描述
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * @Title: getMessage
     * @Description: 方法描述
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * @Title: getStatusMessageByCode
     * @Description: 方法描述
     * @param code
     * @return
     */
    public static StatusMessage getStatusMessageByCode(String code) {
        for (StatusMessage value : StatusMessage.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        return null;
    }
}
