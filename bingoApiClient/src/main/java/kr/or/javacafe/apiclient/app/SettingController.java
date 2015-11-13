package kr.or.javacafe.apiclient.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.javacafe.apiclient.config.prop.DatabaseProperty;
import kr.or.javacafe.apiclient.config.prop.SystemProperty;

@Controller
public class SettingController {

	@Autowired
	private Environment env;
	
	@Autowired
	private DatabaseProperty databaseProp;
	
	@Autowired
	private SystemProperty systemPro;

	
	@RequestMapping("setting")
	public String setting(ModelMap model) {
		model.addAttribute("profile", Arrays.toString(env.getActiveProfiles()));
		
		model.addAttribute("driverClassName", databaseProp.getDriverClassName());
		model.addAttribute("url", databaseProp.getUrl());
		model.addAttribute("username", databaseProp.getUsername());
		model.addAttribute("password", databaseProp.getPassword());
		
		model.addAttribute("name", systemPro.getName());
		model.addAttribute("domain", systemPro.getDomain());
		model.addAttribute("machineName", systemPro.getMachineName());
		model.addAttribute("ip", systemPro.getIp());
		model.addAttribute("listenQueueName", systemPro.getListenQueueName());
		
		return "setting";
	}
	
}
