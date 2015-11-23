package kr.or.javacafe.bingo.app;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
		
	
	@RequestMapping("/")
	public String index(Device device, ModelMap model) {
		if (device.isMobile()) {
			model.addAttribute("_deviceType", "MOBILE");
        } else if (device.isTablet()) {
        	model.addAttribute("_deviceType", "TABLET");
        } else {
        	model.addAttribute("_deviceType", "PC");     
        }
		
		return "index";
	}
	

	@RequestMapping("/setting")
	public String setting(ModelMap model) {
		return "/env/setting";
	}
	
	
	@RequestMapping("/ui")
	public String ui(ModelMap model) {
		return "/env/ui";
	}
	
	
}
