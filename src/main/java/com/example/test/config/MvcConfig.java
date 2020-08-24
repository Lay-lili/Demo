package com.example.test.config;

import com.example.test.Filter.MyFilter;
import com.example.test.interceptor.Myinterceptor;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }


    /**
     * 将过滤器注册到Spring ioc容器
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration(){
        FilterRegistrationBean register = new FilterRegistrationBean();
        register.setFilter(new MyFilter());
        register.addUrlPatterns("/*");
        register.addInitParameter("参数名","参数值");
        register.setName("MyFilter");
        register.setOrder(1);
        return register;
    }

    /**
     * 将拦截器注册到Spring ioc容器
     * @return
     */
    @Bean
    public Myinterceptor myinterceptor(){
        return new Myinterceptor();
    }

    /**
     * 重写该方法，往拦截器链添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加myinterceptor拦截器，并设置拦截器路径为/*
        registry.addInterceptor(myinterceptor()).addPathPatterns("");
    }
}
