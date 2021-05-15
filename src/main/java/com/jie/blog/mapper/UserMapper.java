package com.jie.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.blog.pojo.User;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    List<User> getAll();
}
