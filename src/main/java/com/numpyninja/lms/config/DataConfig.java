package com.numpyninja.lms.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.numpyninja.lms")
@EntityScan(basePackages = "com.numpyninja.lms.entity")
@EnableJpaRepositories(basePackages = "com.numpyninja.lms.repository")
public class DataConfig {

}
