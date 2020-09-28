package com.springcloud.dubbo.sample.api.response;

import java.io.Serializable;

/**
 * 服务接口返回类基类
 * @author manco
 * @since 2020-09-27 10:06:52
 */
public class SampleResponse implements Serializable {

    private static final long serialVersionUID = -5966318491506756744L;

    public SampleResponse() {
        errorCode = "0";
        errorMsg = "success";
    }

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误描述
     */
    private String errorMsg;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
