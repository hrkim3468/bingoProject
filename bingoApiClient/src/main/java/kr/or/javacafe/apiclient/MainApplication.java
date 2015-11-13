package kr.or.javacafe.apiclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, addConfigFile(args));
    }
    
    
    private static String[] addConfigFile(String[] args) {
    	List<String> list = new ArrayList<String>(Arrays.asList(args));
    	list.add("--spring.config.location=classpath:application-db-config.yml,classpath:application-system-config.yml");
    	
    	return list.toArray(new String[]{});
    }
    
    
}
