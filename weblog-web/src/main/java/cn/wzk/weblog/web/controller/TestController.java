package cn.wzk.weblog.web.controller;

import cn.wzk.weblog.common.aspect.ApiOperationLog;
import cn.wzk.weblog.common.enums.ResponseCodeEnum;
import cn.wzk.weblog.common.exception.BizException;
import cn.wzk.weblog.common.util.JsonUtil;
import cn.wzk.weblog.common.util.Response;
import cn.wzk.weblog.web.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @author wangzk16
 * @date 2026年06月25日 18:11
 */
@RestController
@Api(tags = "首页模块")
@Slf4j
public class TestController {

    private final View error;

    public TestController(View error) {
        this.error = error;
    }

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation("测试接口")
    public Response<User> test(@RequestBody @Valid User user) {
        // 手动抛自定义业务异常
        // throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);

        // 抛运行时异常
//        int r = 1 / 0;

        // 打印入参
        log.info(JsonUtil.toJsonString(user));

        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());


        return Response.success(user);
    }
}
