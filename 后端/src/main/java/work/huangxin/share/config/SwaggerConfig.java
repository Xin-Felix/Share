package work.huangxin.share.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Data
@Configuration
@EnableSwagger2
@ConditionalOnClass(EnableSwagger2.class)
@ConfigurationProperties(prefix = "swagger")  //获取yml配置
public class SwaggerConfig {
    /**
     * API接口包路径
     */
    private String basePackage;

    /**
     * API页面标题
     */
    private String title;

    /**
     * API描述
     */
    private String description;

    /**
     * 服务条款地址
     */
    private String termsOfServiceUrl;

    /**
     * 版本号
     */
    private String version;

    /**
     * 联系人
     */
    private Contact contact;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .version(version)
                .contact(contact)
                .build();
    }

}

