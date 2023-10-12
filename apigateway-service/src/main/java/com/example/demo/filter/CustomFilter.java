package com.example.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

// 여기서 만든 파일은 yml 파일에 등록해준다.
@Component
@Slf4j									// 데이터타입이 현재 만들고 있는 커스텀 필터 컨피그가
										// 매개변수로 들어간다. CustomFilter
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config>{
	
	
	
	public CustomFilter() {
		super(Config.class);
	}
//	
	public static class Config {
		// Put the Configuration properties
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request =  exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();
			
			log.info("Custom PRE filter: request Id -> {}", request.getId());
			
			// Custom Post Filter
			return chain.filter(exchange).then(Mono.fromRunnable(() ->{
				log.info("Custom POST filter: rsponse code -> {}", response.getStatusCode());
			}));
			
		};
	}
	
}
