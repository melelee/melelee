package com.melelee.melelee;

import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


/**
 * @author melelee
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableSwagger2Doc
@EnableTransactionManagement
public class MeleleeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeleleeApplication.class, args);
    }

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }
}
