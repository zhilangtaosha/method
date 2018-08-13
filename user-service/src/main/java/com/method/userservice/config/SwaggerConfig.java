package com.method.userservice.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestfulApi() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();

        parameterBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("token") //参数名
                .defaultValue("asdf") //默认值
                .description("可以不用提供token")
                .modelRef(new ModelRef("String"))//指定参数值的类型
                .required(false)
                .build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameterBuilder.build());

        parameterBuilder.parameterType("header")
                .name("api-key")
                .defaultValue("123")
                .description("一定要提供api-key")
                .modelRef(new ModelRef("String"))
                .required(true)
                .build();
        parameterList.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).globalOperationParameters(parameterList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ifeng.zlphb.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("use swagger generate restful api document")
                .description("set it up by naison")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
