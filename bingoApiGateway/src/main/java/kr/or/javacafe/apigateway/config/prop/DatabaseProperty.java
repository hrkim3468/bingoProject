package kr.or.javacafe.apigateway.config.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
	
	
	
	
	/**
	 * {@link #driverClassName} 을(를) 반환한다.
	 * @return {@link #driverClassName}
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	/**
	 * {@link #driverClassName} 을(를) 설정한다.
	 * @param driverClassname 설정할 {@link #driverClassName}
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	/**
	 * {@link #url} 을(를) 반환한다.
	 * @return {@link #url}
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * {@link #url} 을(를) 설정한다.
	 * @param url 설정할 {@link #url}
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * {@link #username} 을(를) 반환한다.
	 * @return {@link #username}
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * {@link #username} 을(를) 설정한다.
	 * @param username 설정할 {@link #username}
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * {@link #password} 을(를) 반환한다.
	 * @return {@link #password}
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * {@link #password} 을(를) 설정한다.
	 * @param password 설정할 {@link #password}
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
