package com.heu.finance;

import com.heu.finance.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/","/Register/**","/bootstrap/**","/js/**",
                "/lyear/**", "/images/**","/loginVerifyUsername/**","/verifyLogin/**");
    }
}
