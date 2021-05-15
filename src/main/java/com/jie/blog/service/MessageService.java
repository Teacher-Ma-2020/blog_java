package com.jie.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jie.blog.pojo.Message;

import java.util.List;

public interface MessageService extends IService<Message> {
    public List<Message> getAlls();
}
