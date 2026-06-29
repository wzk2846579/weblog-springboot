package cn.wzk.weblog.common.aspect;

import java.lang.annotation.*;

/**
 * @author wangzk16
 * @date 2026年06月20日 17:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiOperationLog {

    /**
     * API功能描述
     * @author wangzk16
     * @date 2026/6/23 15:07
     * @return java.lang.String
     */
    String description() default "";
}
