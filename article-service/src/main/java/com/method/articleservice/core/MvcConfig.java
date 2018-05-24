package com.method.articleservice.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.method.articleservice.controllers.interceptor.SecurityIntercepter;
import com.method.articleservice.controllers.interceptor.ValidationIntercepter;

@SuppressWarnings("deprecation")
@EnableWebMvc
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ValidationIntercepter validationIntervepter() {
		return new ValidationIntercepter();
	}

	@Bean
	SecurityIntercepter securityIntercepter() {
		return new SecurityIntercepter();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(validationIntervepter());
		registry.addInterceptor(securityIntercepter());
	}

}
