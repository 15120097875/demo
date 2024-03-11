package com.mylock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mylock.entity.Aj;
import com.mylock.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class RedisDemo {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		List<Aj> ajList = new ArrayList<>();
		Aj aJ1 = new Aj();
		aJ1.setWaje(new BigDecimal("60232.70"));
		ajList.add(aJ1);
		Aj aJ2 = new Aj();
		aJ2.setWaje(new BigDecimal("21980.23"));
		ajList.add(aJ2);
		Aj aJ3 = new Aj();
		aJ3.setWaje(new BigDecimal("48623.48"));
		ajList.add(aJ3);
		Aj aJ4 = new Aj();
		aJ4.setWaje(new BigDecimal("94360.37"));
		ajList.add(aJ4);
		Aj aJ5 = new Aj();
		aJ5.setWaje(new BigDecimal("67396.45"));
		ajList.add(aJ5);

		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setUsername("张三");
		user1.setBl("34");
		userList.add(user1);
		User user2 = new User();
		user2.setUsername("李四");
		user2.setBl("33");
		userList.add(user2);
		User user3 = new User();
		user3.setUsername("王五");
		user3.setBl("33");
		userList.add(user3);

		redisTemplate.opsForList().leftPushAll("1",userList.toArray());
		redisTemplate.expire("1",60,TimeUnit.SECONDS);
		List<User> list = redisTemplate.opsForList().range("1", 0, -1);
		System.out.println("1:"+list);


		redisTemplate.opsForValue().set("2", JSON.toJSON(userList).toString(),60, TimeUnit.SECONDS);
		Object o = redisTemplate.opsForValue().get("2");
		List<User> businessIdList = JSONObject.parseArray(o.toString(), User.class);
		System.out.println("2:"+businessIdList);
	}

}
