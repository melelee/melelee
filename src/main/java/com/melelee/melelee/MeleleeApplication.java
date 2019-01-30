package com.melelee.melelee;

import com.didispace.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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


	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

		//Set the scheme that will be assigned to requests received through this connector
		//@param scheme The new scheme
		connector.setScheme("http");

		//Set the port number on which we listen for requests.
		// @param port The new port number
		connector.setPort(80);

		//Set the secure connection flag that will be assigned to requests received through this connector.
		//@param secure The new secure connection flag
		//if connector.setSecure(true),the http use the http and https use the https;else if connector.setSecure(false),the http redirect to https;
		connector.setSecure(false);

		//redirectPort The redirect port number (non-SSL to SSL)
		connector.setRedirectPort(443);
		return connector;
	}
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				//Due to CONFIDENTIAL and /*, this will cause Tomcat to redirect every request to HTTPS.
				//You can configure multiple patterns and multiple constraints if you need more control over what is and is not redirected.

				SecurityConstraint constraint = new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;

	}
}
