package com.spring.sample.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.spring.sample.controller")
@EnableWebMvc
public class WebApplicationConfig {

}
