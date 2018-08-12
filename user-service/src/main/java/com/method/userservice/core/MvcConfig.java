package com.method.userservice.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.method.userservice.controllers.interceptor.SecurityIntercepter;
import com.method.userservice.controllers.interceptor.ValidationIntercepter;

//@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

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
