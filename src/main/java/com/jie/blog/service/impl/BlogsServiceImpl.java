package com.jie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jie.blog.mapper.BlogsMapper;
import com.jie.blog.pojo.Blogs;
import com.jie.blog.service.BlogsService;
import org.springframework.stereotype.Service;

@Service
public class BlogsServiceImpl extends ServiceImpl<BlogsMapper, Blogs> implements BlogsService {
}
