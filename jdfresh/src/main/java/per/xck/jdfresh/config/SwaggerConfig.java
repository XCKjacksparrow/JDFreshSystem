package per.xck.jdfresh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2         // 开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("X");
    }

    // 配置了Swagger的Docket的实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("夏崇康")
                .select()
                // RequestHandlerSelectors, 配置要扫描接口的方式
                // basePackage: 制定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("per.xck.jdfresh.controller"))
                // paths(): 过滤
//                .paths(PathSelectors.ant("/xck/**"))
                .build();       //build 工厂模式
    }

    // 配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("kazemi","kazemi.cn","1367173922@qq.com");
        return new ApiInfo("JDfresh的Api文档",
                "Api Documentation",
                "1.0",
                "http://kazemi.cn",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
