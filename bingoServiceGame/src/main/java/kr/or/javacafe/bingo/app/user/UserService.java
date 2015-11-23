package kr.or.javacafe.bingo.app.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	

	public User find(Long id) {
		return repository.findOne(id);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	


}
