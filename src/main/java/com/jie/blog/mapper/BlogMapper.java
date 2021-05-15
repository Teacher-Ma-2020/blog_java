package com.jie.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.blog.pojo.Blog;
import com.jie.blog.pojo.Comment;
import org.apache.tomcat.jni.User;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    int addView(Long id,int count);
    Blog getBlog(Long id);
    List<Comment> getCommentByid(Long id);
    User getUser(Long id);
    int addCom(Long id);
    List<Blog> getHot();
}
