package com.mylock;

import com.mylock.entity.User;
import com.mylock.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		User byId = userService.getById(1);
		System.out.println(byId);
		log.info("成功");
	}

}
