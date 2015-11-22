package kr.or.javacafe.core.spring.component;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

	public String getHello() {
		return "HelloWorld!!!";
	}
	
}
