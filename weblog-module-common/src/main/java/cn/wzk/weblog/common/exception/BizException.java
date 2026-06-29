package cn.wzk.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wangzk16
 * @date 2026年06月27日 16:01
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public BizException(BaseExceptionIntf baseException) {
        this.errorCode = baseException.getErrorCode();
        this.errorMsg = baseException.getErrorMsg();
    }
}
