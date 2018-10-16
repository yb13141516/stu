package cn.tedu;

import org.springframework.boot.SpringApplication;

//放在所有需要处理类等文件的父目录
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class RunApp {
	public static void main(String[] args) {
		SpringApplication.run(RunApp.class, args);
	}
}
