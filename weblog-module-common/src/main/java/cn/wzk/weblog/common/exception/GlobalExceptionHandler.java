package cn.wzk.weblog.common.exception;

import cn.wzk.weblog.common.enums.ResponseCodeEnum;
import cn.wzk.weblog.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wangzk16
 * @date 2026年06月27日 16:11
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request, BizException bizException) {
        log.warn("{} request fail, errorCode = {}, errorMsg = {}！", request.getRequestURI(), bizException.getErrorCode(), bizException.getErrorMsg());
        return Response.fail(bizException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        String errorCode = ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode();
        BindingResult bindingResult = exception.getBindingResult();
        String errorMsg = Optional.ofNullable(bindingResult.getFieldErrors()).orElse(new ArrayList<>())
                .stream()
                .map(error -> String.format("%s %s，当前值：%s", error.getField(), error.getDefaultMessage(), error.getRejectedValue()))
                .collect(Collectors.joining("；"));
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMsg);
        return Response.fail(errorCode, errorMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<Object> handleOtherException(HttpServletRequest request, Exception exception) {
        log.error("{} request fail！", request.getRequestURI(), exception);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }
}
