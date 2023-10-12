package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/second-service/") // apigateway-service 에서 yml Path 정의를 수정했기 때문에 맞춰준다.
public class SecondServiceController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the Second Service";
	}
	
	@GetMapping("/message")
	public String message(@RequestHeader("second-request") String header) {
		log.info(header);
		return "Hello World in Second Service.Message";
	}
}
 