package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/first-service") // apigateway-service 에서 yml Path 정의를 수정했기 때문에 맞춰준다.
public class FirstServiceController {
	
	/*
	 yml파일에 등록되어진 환설정 정보를 가져오는 방법
	 1. Environment
	 */
	
//	@Autowired
//	Environment env;
	
	Environment env;
	
	@Autowired
	public FirstServiceController(Environment env) {
		this.env = env;
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the First Service";
	}
	
	@GetMapping("/message")
	public String message(@RequestHeader("first-request") String header) {
		log.info(header);
		return "Hello World in First Service.Message";
	}
	
	@GetMapping("/check")
	public String check(HttpServletRequest request) {
		log.info("ServerPort = {}", request.getServerPort());
		
		return String.format("Hi, there. This is a message from First Service.check on PORT %s", env.getProperty("local.server.port"));
	}
}