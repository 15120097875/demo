package com.mylock.controller;

import com.mylock.service.UserService;
import com.mylock.util.R;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private StringRedisTemplate redisTemplate;

    @GetMapping("/get/{id}")
    public R<?> get(@PathVariable Integer id) {
        return R.ok(userService.getById(id));
    }

    // redis锁
    public static void main(String[] args) {



    }



}
