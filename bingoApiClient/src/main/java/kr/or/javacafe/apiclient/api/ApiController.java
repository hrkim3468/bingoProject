package kr.or.javacafe.apiclient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.javacafe.apiclient.test.QueueMessageSender;

@RestController
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	private QueueMessageSender testService;
	
	
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	String hello() {
		return "hello world";
	}
	
	
	@RequestMapping(value = "customer/{id}", method = RequestMethod.GET)
	String customer(@PathVariable Integer id) {
		return "hello world";
	}

	
	@RequestMapping(value = "queue/{id}", method = RequestMethod.GET)
	String queue(@PathVariable Integer id) {
		try {
			testService.send("hello world");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";
	}
	
	
}


















