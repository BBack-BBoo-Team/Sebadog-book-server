package com.book.app.infra.config;

import com.book.app.modules.global.aspect.ApiLogAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApiLogAop logAop() {
        return new ApiLogAop();
    }
}
