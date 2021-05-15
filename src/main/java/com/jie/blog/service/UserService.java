package com.jie.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jie.blog.pojo.User;

import java.util.List;


public interface UserService extends IService<User> {
    List<User> getAll();
}
