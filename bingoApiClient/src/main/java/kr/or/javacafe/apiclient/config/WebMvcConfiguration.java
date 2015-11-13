package kr.or.javacafe.apiclient.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring 설정을 위해 제공되던 ApplicationContext.xml을 JavaConfig로 제공한다.
 * 
 * @author hrkim
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

	private static final Log logger = LogFactory.getLog(WebMvcConfiguration.class);
	private static final int CACHE_PERIOD = (60*60*24) * 365;	// 365일

	@Autowired
	private Environment env;
	
	@Value("${app.version:}")
	private String appVersion;
	
	
	

	/**
	 * SpringBoot에서 기본적으로 제공하는 Resource 설정
	 */
	/*
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", 
			"classpath:/resources/",
			"classpath:/static/", 
			"classpath:/public/"
	};
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}

		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		
		super.addResourceHandlers(registry);
	}
	*/
	
	
	/**
	 * WebJar 형태의 FrontEnd를 위한 Resource 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		boolean localMode;
		if (null == this.env.getActiveProfiles() || this.env.getActiveProfiles().length == 0) {
			localMode = true;
			logger.info("활성화된 Profile : local (null)");
		} else {
			localMode = this.env.acceptsProfiles("local");
			logger.info("활성화된 Profile : " + this.env.getActiveProfiles()[0]);
		}		
		
		String location = localMode ? "file:///C:/Users/NAVER.AL010/git/bingoProject/bingoFrontend/src/" : "classpath:/static/";
		Integer cachePeriod = localMode ? 0 : CACHE_PERIOD;

		// WebJar 형태의 내부 Resource
		registry.addResourceHandler("/bingo/**").addResourceLocations(location).setCachePeriod(cachePeriod);
		
		// WebJar 형태의 외부 Resource
		registry.addResourceHandler("/appjars/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(cachePeriod);
        
		super.addResourceHandlers(registry);
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
