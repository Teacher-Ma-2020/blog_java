package com.jie.blog.controller;

import com.jie.blog.common.dto.CommentDto;
import com.jie.blog.common.lang.Result;
import com.jie.blog.mapper.BlogMapper;
import com.jie.blog.pojo.Blog;
import com.jie.blog.pojo.Comment;
import com.jie.blog.service.BlogService;
import com.jie.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 添加评论
     * @param commentDto
     * @return
     */
    @PostMapping("/addComment")
    public Result addComment(@RequestBody CommentDto commentDto){
        Comment comment=new Comment(commentDto.getContent(),commentDto.getUser_id(),commentDto.getBlog_id());
        comment.setCreated(LocalDateTime.now());
        Long blog_id = commentDto.getBlog_id();
        blogService.addCom(blog_id);
        boolean save = commentService.save(comment);
        Blog blog = blogMapper.getBlog(commentDto.getBlog_id());
        System.out.println(blog);
        redisTemplate.opsForValue().set("blog:getBlogById" + commentDto.getBlog_id(),blog);
        return Result.success(save);
    }

    @GetMapping("/deleteById")
    public Result deleteById(Long id,Long blog_id){
        commentService.removeById(id);
        blogService.deleteCom(blog_id);
        Blog blog = blogMapper.getBlog(blog_id);
        return Result.success(null);
    }
}
