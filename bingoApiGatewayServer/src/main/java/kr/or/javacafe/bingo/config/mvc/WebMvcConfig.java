package kr.or.javacafe.bingo.config.mvc;

import java.util.List;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.or.javacafe.core.spring.interceptor.ResponseTimeCheckInterceptor;
import kr.or.javacafe.core.spring.interceptor.UITemplateInterceptor;


/**
 * 스프링 MVC 설정을 위한 ApplicationContext 설정을 JavaConfig로 변환
 * 
 * @author hrkim
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(addDeviceHandlerMethodArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(addUITemplateInterceptor());
		registry.addInterceptor(addResponseTimeCheckInterceptor());		
		super.addInterceptors(registry);
	}


	/**
	 * [ArgResolver] UserAgent 정보 분석 제공 
	 * @return
	 */
	@Bean
	public DeviceHandlerMethodArgumentResolver addDeviceHandlerMethodArgumentResolver() {
		return new DeviceHandlerMethodArgumentResolver();
	}

	
	/**
	 * [Interceptor] UI 템플릿 인터셉터
	 * @return
	 */
	@Bean
	public UITemplateInterceptor addUITemplateInterceptor() {
		return new UITemplateInterceptor();
	}
	
	
	/**
	 * [Interceptor] 응답시간 측정 인터셉터
	 * @return
	 */
	@Bean
	public ResponseTimeCheckInterceptor addResponseTimeCheckInterceptor() {
		return new ResponseTimeCheckInterceptor();
	}

	
	/**
	 * ViewResolver를 JSP로 설정
	 * @return
	 */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    
    /**
     * Custom Tomcat 설정 활성화
     * @return
     */
    @Bean
    public ServerProperties initServerProperties() {
    	return new TomcatServerInitializer();
    }
    
    
    
    
}
