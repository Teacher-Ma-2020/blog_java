package com.jie.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jie.blog.mapper.BlogMapper;
import com.jie.blog.pojo.Blog;
import com.jie.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public int addView(Long id, int count) {
        return blogMapper.addView(id,count);
    }

    @Override
    public Blog getBlogById(Long id) {
        //查询redis是否存在数据列表
        Blog blog;
        blog = (Blog) redisTemplate.opsForValue().get("blog:getBlogById" + id);
        if (blog==null){
            blog = blogMapper.getBlog(id);
            redisTemplate.opsForValue().set("blog:getBlogById" + id,blog);
            System.out.println(blog.getTitle()+"来自数据库");
            return blog;
        }

        System.out.println(blog.getTitle()+"来自redis");
        return blog;
    }

    @Override
    public int addCom(Long id) {
        return blogMapper.addCom(id);
    }

    @Override
    public int deleteCom(Long id) {
        return blogMapper.deleteCom(id);
    }

    @Override
    public List<Blog> getHot() {
        return blogMapper.getHot();
    }


}
