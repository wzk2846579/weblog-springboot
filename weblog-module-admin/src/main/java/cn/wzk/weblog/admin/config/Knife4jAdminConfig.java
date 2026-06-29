package cn.wzk.weblog.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author wangzk16
 * @date 2026年06月28日 16:29
 */
@Configuration
@Profile("dev")
@EnableSwagger2WebMvc
public class Knife4jAdminConfig {

    @Bean("adminApi")
    public Docket crateAdminDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //.apiInfo(buildApiInfo())
                .groupName("Admin 后台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.wzk.weblog.admin.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("weblog 博客后台接口文档")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。") // 描述
                .termsOfServiceUrl("https://www.quanxiaoha.com/") // API 服务条款
                .contact(new Contact("犬小哈", "https://www.quanxiaoha.com", "871361652@qq.com")) // 联系人
                .version("1.0") // 版本号
                .build();
    }
}
