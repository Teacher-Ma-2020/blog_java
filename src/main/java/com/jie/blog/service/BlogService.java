package com.jie.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jie.blog.pojo.Blog;

import java.util.List;


public interface BlogService extends IService<Blog> {
    int addView(Long id,int count);
    Blog getBlogById(Long id);
    int addCom(Long id);
    List<Blog> getHot();
}
