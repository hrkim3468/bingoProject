package kr.or.javacafe.core.spring.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 리소스 디렉토리에 존재하는 application-db-config.yml 파일에 정의된 정보를
 * profile 정보에 의해 동적으로 맵핑해 POJO로 돌려준다.
 * 
 * @author hrkim
 *
 */
@ConfigurationProperties(prefix = "db")
@Component
public class DatabaseProperty {

	/**
	 * 드라이버
	 */
	private String driverClassName;
	/**
	 * 드라이버 URL
	 */
	private String url;
	/**
	 * DB User
	 */
	private String username;
	/**
	 * DB User 비밀번호
	 */
	private String password;
	
	
	
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	


	
	
}
