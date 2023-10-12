package com.example.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config>{

	public LoggingFilter() {
		super(Config.class);
	}
	
	@Data
	public static class Config{
		private String baseMessage;
		private boolean preLogger;
		private boolean postLogger;
	}

	@Override
	public GatewayFilter apply(Config config) {
//		return (exchange, chain) -> {
//			ServerHttpRequest request = exchange.getRequest();
//			ServerHttpResponse response = exchange.getResponse();
//			
//			log.info("Logging Filter baseMessage : {}", config.getBameMessage());
//			
//			if(config.isPreLogging()) {
//				log.info("Logging Filter Start : request id -> {}", request.getId());
//			}
//			
//			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//				if(config.isPostLogging()) {
//					log.info("Logging Filter End : response code ->  {}", response.getStatusCode());
//				}
//			}));
//		};
//		GatewayFilter filter = null;
		// OrderedGatewayFilter 는 필터를 구현시켜주는 자식 클래스 용도의 역할을 하고 있다.
		GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
			
			ServerHttpRequest request = exchange.getRequest();
			ServerHttpResponse response = exchange.getResponse();
			log.info("Logging Filter baseMessage : {}", config.getBaseMessage());
			
			if(config.isPreLogger()) {
				log.info("Logging Filter : request id -> {}", request.getId());
			}
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				if(config.isPostLogger()) {
					log.info("Logging Filter : response code ->  {}", response.getStatusCode());
				}
			}));
			
		}, Ordered.LOWEST_PRECEDENCE); // 필터의 우선순위를 정할 수 있는 것을 놓으면 된다. 우선순위 조정가능
		
		/*
		 OrderedGatewayFilter 안의 filter 메서드를 보게 되면
		 filter 메서드가 재정의 됨으로써 이 필터가 해야될 역할을 여기에 저장할 수가 있다.
		 ServerWebExchange 객체
		 	스프링에서 새롭게 도입이 된 WebFlux 라는 기능을 사용하게 되면 사용할 수 있는 개체이지만
		 	WebFlux 에서는 더이성 http 서블릿과 리쿼스트를 지원하지 않는다.
		 */
		return filter;
	}
}
