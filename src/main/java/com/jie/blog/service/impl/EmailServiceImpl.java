package com.jie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jie.blog.mapper.EmailMapper;
import com.jie.blog.pojo.Email;
import com.jie.blog.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {
}
