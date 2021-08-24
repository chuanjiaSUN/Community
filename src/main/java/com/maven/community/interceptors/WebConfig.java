package com.maven.community.interceptors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sunchuanjia
 * @Description
 * @create 2021-08-18 15:13
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public SessionInterceptor getSessionInterceptor()
    {
        return new SessionInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/fonts/**","/js/**","/image/**");
    }
}
