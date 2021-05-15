package com.jie.blog.service.impl;

import com.jie.blog.pojo.Blog;
import com.jie.blog.mapper.BlogMapper;
import com.jie.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public int addView(Long id, int count) {
        return blogMapper.addView(id,count);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public int addCom(Long id) {
        return blogMapper.addCom(id);
    }

    @Override
    public List<Blog> getHot() {
        return blogMapper.getHot();
    }


}
