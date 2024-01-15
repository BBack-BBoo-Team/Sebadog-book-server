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
		return "안녕하세요 세바개 팀입니다. 독서 모임 관리 및 기록 앱 [세바독]을 기대해주세요. \n 해당 API는 세바독 server 연동 테스트 API입니다.";
	}
}
