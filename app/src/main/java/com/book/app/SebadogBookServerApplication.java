package com.book.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SebadogBookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SebadogBookServerApplication.class, args);
	}

	@GetMapping("/test")
	public String test() throws Exception {
		return "Hello Test!";
	}
}
