package com.melelee.melelee;

import com.didispace.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;


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


	@Bean(name = "sslRestTemplate")
	public RestTemplate sslRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, CertificateException, IOException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		File file = new File("config/iot.p12");
		//如果配置文件不存在，从classpath加载配置
		String passwd = "123123";
		//读取证书
		try (FileInputStream instream = new FileInputStream(file)) {
			keyStore.load(instream, passwd.toCharArray());
		}
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy()).build();

		//不进行https主机域名认证，就是把https后面的域名与证书的域名匹配，如果需要可以实现自己的域名认证
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return new RestTemplate(requestFactory);
	}
}
