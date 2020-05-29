package com.rodrigo.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);
		//System.out.println("Senha : " + new BCryptPasswordEncoder().encode("123"));
	}



}
