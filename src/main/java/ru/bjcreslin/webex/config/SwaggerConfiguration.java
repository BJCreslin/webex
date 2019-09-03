package ru.bjcreslin.webex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.bjcreslin.webex.controller"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(getInfo());
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Api description",
                "Api description",
                "1.0",
                "bjcreslin.ru",
                new Contact("Vladimir Ju Kreslin",
                        "http://bjcreslin.ru",
                        "bjcreslin@gmail.com"),
                "Apache license version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.singleton(new VendorExtension() {
                    @Override
                    public String getName() {
                        return "Vladimir Ju Kreslin";
                    }

                    @Override
                    public Object getValue() {
                        return "1";
                    }
                })
        );
    }
}
