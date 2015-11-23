package kr.or.javacafe.bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.or.javacafe.core.spring.filter.AccessLogFilter;

/**
 * SpringBoot 프로그램의 시작점
 * 
 * Web.xml을 JavaConfig로 변환하기 위해 제공되는 org.springframework.web.WebApplicationInitializer 인터페이스를 
 * SpringBoot에 맞도록 확장하였기 때문에 필요한 Filter와 Servlet을 여기에서 등록한다.
 * 
 * - Filter 등록시 : FilterRegistrationBean
 * - Servlet 등록시 : ServletRegistrationBean
 * 
 * @author hrkim
 *
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
    	SpringApplication.run(MainApplication.class, addConfigFile(args));
    }
    
    
    /**
     * 프로그램 실행시 필요한 파라메터를 추가한다.
     * @param args
     * @return
     */
    private static String[] addConfigFile(String[] args) {
    	List<String> list = new ArrayList<String>(Arrays.asList(args));
    	list.add("--spring.config.location=classpath:application-db-config.yml,classpath:application-system-config.yml");
    	
    	return list.toArray(new String[]{});
    }
    

	
    /**
     * Encoding 처리를 위한 필터 추가
     * @return
     */
    @Bean
    public FilterRegistrationBean utf8CharacterEncodingFilter() {
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
    	filter.setEncoding("UTF-8");
    	filter.setForceEncoding(true);
    	
    	FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    	registrationBean.setFilter(filter);
    	registrationBean.addUrlPatterns("/*");
    	
    	return registrationBean;
    }
    

    /**
     * AccessLog 로깅을 위한 필터 추가
     * @return
     */
    @Bean
    public FilterRegistrationBean accessLogLoggingFilter() {
    	AccessLogFilter filter = new AccessLogFilter();
    	
    	FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    	registrationBean.setFilter(filter);
    	registrationBean.addUrlPatterns("/*");
    	
    	return registrationBean;
    }

    
}














