package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.mysite.service", "com.douzone.mysite.repository", "com.douzone.mysite.aspect"})
public class AppConfig {

	
}
