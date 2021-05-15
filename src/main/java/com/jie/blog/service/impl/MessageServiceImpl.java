package com.jie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jie.blog.mapper.MessageMapper;
import com.jie.blog.pojo.Message;
import com.jie.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>  implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getAlls() {
        return messageMapper.getAlls();
    }
}
