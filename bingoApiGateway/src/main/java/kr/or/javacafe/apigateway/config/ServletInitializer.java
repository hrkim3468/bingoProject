package kr.or.javacafe.apigateway.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import kr.or.javacafe.apigateway.MainApplication;

/**
 * Web.xml을 JavaConfig로 변환하기 위해 제공되는 org.springframework.web.WebApplicationInitializer 인터페이스를 
 * SpringBoot에 맞도록 확장한 SpringBootServletInitializer를 구현한다.
 * 
 * @author hrkim
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApplication.class);
	}

}
