package cn.wzk.weblog.common.util;

import cn.wzk.weblog.common.exception.BaseExceptionIntf;
import cn.wzk.weblog.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangzk16
 * @date 2026年06月27日 15:00
 */
@Data
public class Response<T> implements Serializable {

    // 是否成功，默认为true
    private Boolean success = true;
    // 响应数据
    private T data;
    // 错误码
    private String errorCode;
    // 响应消息
    private String message;

    // =================================== 成功响应 ===================================
    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }
    // =================================== 失败响应 ===================================
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errorMsg);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String errorMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(errorMsg);
        return response;
    }

    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMsg());
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionIntf baseExceptionIntf) {
        Response<T> response =  new Response<>();
        response.setSuccess(false);
        response.setErrorCode(baseExceptionIntf.getErrorCode());
        response.setMessage(baseExceptionIntf.getErrorMsg());
        return response;
    }
}
