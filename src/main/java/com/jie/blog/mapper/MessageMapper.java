package com.jie.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.blog.pojo.Message;

import java.util.List;

public interface MessageMapper extends BaseMapper<Message> {
    public List<Message> getAlls();
}
