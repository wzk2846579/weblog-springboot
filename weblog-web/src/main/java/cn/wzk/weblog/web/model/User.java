package cn.wzk.weblog.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author wangzk16
 * @date 2026年06月25日 18:10
 */
@Data
@ApiModel("用户实体类")
public class User {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 100, message = "年龄不能超过100岁")
    private Integer age;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private LocalDateTime createTime;

    private LocalDate updateDate;

    private LocalTime time;
}
