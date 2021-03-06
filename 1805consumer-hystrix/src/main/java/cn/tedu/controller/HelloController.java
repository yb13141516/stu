package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.feign.EurekaServiceFeign;

@RestController
public class HelloController {

	@Autowired
	private EurekaServiceFeign feign;
	
	@GetMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod="fallbackHello")
	public String hello(@PathVariable String name) {
		String url = "http://localhost:7900/hello/"+name;   //直接访问
		//使用eureka动态列表,通过application.name找到真正的提供者
		url = "http://provider-user:7900/hello/"+name;   //直接访问
		return this.feign.hello(name);

	}
	//失败时断路器自动回调这个方法
	//方法名字和fallbackMethod名称一致,番薯和返回值要一致
	
	public String fallbackHello(String name) {
		//设置失败时的默认值
		return "tony";
	}
}
