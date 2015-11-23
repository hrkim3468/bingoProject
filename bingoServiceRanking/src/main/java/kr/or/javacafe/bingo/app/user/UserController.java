package kr.or.javacafe.bingo.app.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService service;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findUser(@PathVariable Long id) {
		User user = service.find(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<User> users = service.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	
}
