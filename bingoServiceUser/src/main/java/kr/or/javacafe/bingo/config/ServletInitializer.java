package kr.or.javacafe.bingo.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import kr.or.javacafe.bingo.MainApplication;

/**
 * SpringBoot 어플리케이션을 WAR 형태로 변경하여 기존 톰켓에 배포시 구현해야 하는 구현체
 * 
 * maven 프로젝트의 경우
 * 1. pom.xml에 packaging 타입을 war로 변경
 * 2. spring-boot-start-tomcat 스코프를 provided로 변경
 * 3. SpringBootServletInitializer를 상속하는 구현체 추가
 * 
 * 메뉴얼
 * http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#build-tool-plugins-maven-packaging
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
