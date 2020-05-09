package com.hnist.tos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content 启动类
 */
@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
@EntityScan
public class TosApplication extends SpringBootServletInitializer {
    // war启动请求实现该方法
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(TosApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(TosApplication.class, args);
    }

}
