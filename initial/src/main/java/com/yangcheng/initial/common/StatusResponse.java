package com.yangcheng.initial.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 4329048184249741909L;

    /**
     * 异常状态码.正常情况下没有值
     */
    private String code;

    /**
     * 异常状态下地异常描述.正常情况下没有值
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public void setMsgAndCode(StatusResponseCode statusResponseCode) {
        this.code = statusResponseCode.getCode();
        this.msg = statusResponseCode.getMsg();
    }

}