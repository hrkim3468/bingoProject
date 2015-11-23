package kr.javacafe.core.manager.memory;

import org.junit.Test;

import kr.or.javacafe.core.manager.memoryMap.user.UserManager;

public class memoryTest {

	//@Test
	public void recv() {
		UserManager userManager = new UserManager("http://localhost:9501/api/users");
		userManager.start();		
	}
}
