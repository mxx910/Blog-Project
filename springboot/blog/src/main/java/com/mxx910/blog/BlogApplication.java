package com.mxx910.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author mxx910
 */
@SpringBootApplication
@MapperScan("com.mxx910.blog.mapper")
public class BlogApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(BlogApplication.class, args);

	}

}
