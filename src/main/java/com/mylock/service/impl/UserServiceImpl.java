package com.mylock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylock.entity.User;
import com.mylock.mapper.UserMapper;
import com.mylock.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
