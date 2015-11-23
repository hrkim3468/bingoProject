package kr.or.javacafe.bingo.config.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", 
			"classpath:/resources/",
			"classpath:/static/", 
			"classpath:/public/"
	};
	
	private static final int CACHE_PERIOD = (60*60*24) * 365;	// 365일
	
	@Autowired
	private Environment env;
	
	
	
	
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
    
    
    /**
     * Resource 핸들러 확장
     */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// Default Resource
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}

		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		
		// [추가] Webjar 형태의 Resource
		if (env.acceptsProfiles("local")) {
			registry.addResourceHandler("/appjars/**").addResourceLocations("file:///C:/Users/NAVER.AL010/git/bingoProject/bingoFrontend/dist/").setCachePeriod(0);			
		} else {
			registry.addResourceHandler("/appjars/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(CACHE_PERIOD);
			
		}
        
		// [추가] 로컬 Resource
		//registry.addResourceHandler("/html/**").addResourceLocations("classpath:/resources/html/").setCachePeriod(CACHE_PERIOD);
        //registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/css/").setCachePeriod(CACHE_PERIOD);
        //registry.addResourceHandler("/img/**").addResourceLocations("classpath:/resources/img/").setCachePeriod(CACHE_PERIOD);
        //registry.addResourceHandler("/js/**").addResourceLocations("classpath:/resources/js/").setCachePeriod(CACHE_PERIOD);
		
        //registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/").setCachePeriod(CACHE_PERIOD);
		
		super.addResourceHandlers(registry);
	}

    
}










