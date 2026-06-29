package cn.wzk.weblog.web.config;

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
 * @date 2026年06月28日 01:12
 * Knife4j配置
 */
@Configuration
@Profile("dev")
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean("webApi")
    public Docket createApiDoc() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 分组名称
                .groupName("WEB 前台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.wzk.weblog.web.controller"))
                //.paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建API信息
     * @author wangzk16
     * @date 2026/6/28 15:56
     * @return springfox.documentation.service.ApiInfo
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("weblog 博客前台接口文档")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                .termsOfServiceUrl("https://www.quanxiaoha.com/")
                .contact(new Contact("犬小哈", "https://www.quanxiaoha.com/", "871361652@qq.com"))
                .version("1.0")
                .build();
    }
}
