package com.example.greengram2.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//설정하는 클래스
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo(){
        return new Info().title("Greengram Ver.2")
                .description("인스타그램 클론 코딩")
                .version("2.0.0");
    }
    //swagger에 제목 등등을 만들 수 있는 코드
}
