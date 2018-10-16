package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//标识这个服务是一个Eureka客户端
public class ProviderUserRunApp2 {

	public static void main(String[] args) {
		SpringApplication.run(ProviderUserRunApp2.class, args);

	}

}
