package com.example.demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 필터를 컴포넌트로 등록한다. 빈을 스테레오 타입 컴포넌트로 등록한다.
public class ZuulLoggingFilter extends ZuulFilter {

//	Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("************************** Printing logs: ");
		
		// 요청 최상위 객체
		RequestContext ctx =  RequestContext.getCurrentContext();
		
		HttpServletRequest request = ctx.getRequest();
		
		log.info("************************** " + request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		// 여러개의 필터가 존재할 때 순서를 이야기 한다.
		// 현재 필터가 1개만 존재하기 때문에 1을 사용
		return 1;
	}

}
