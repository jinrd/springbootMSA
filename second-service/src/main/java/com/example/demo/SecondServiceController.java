package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service/") // apigateway-service 에서 yml Path 정의를 수정했기 때문에 맞춰준다.
public class SecondServiceController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the Second Service";
	}
}
 