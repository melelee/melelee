package com.melelee.melelee;

import com.didispace.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author melelee
 */
@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.melelee.melelee.mapper")
@EnableScheduling
@EnableTransactionManagement
@EnableSwagger2Doc
public class MeleleeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeleleeApplication.class, args);
	}

	/**
	 * 会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
	 * 要注意，如果使用独立的servlet容器，
	 * 而不是直接使用springboot的内置容器，
	 * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}


	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	// 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}
}
