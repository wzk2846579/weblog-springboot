package cn.wzk.weblog.common.enums;

import cn.wzk.weblog.common.exception.BaseExceptionIntf;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应异常码
 * @author wangzk16
 * @date 2026年06月27日 15:53
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionIntf {
    // 系统异常状态码
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),

    // 参数无效异常状态码
    PARAM_NOT_VALID("10001", "参数无效"),

    // 业务异常状态码
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）");

    private String errorCode;

    private String errorMsg;
}
