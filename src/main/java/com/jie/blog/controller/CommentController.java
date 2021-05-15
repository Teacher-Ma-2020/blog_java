package com.jie.blog.controller;

import com.jie.blog.common.dto.CommentDto;
import com.jie.blog.common.lang.Result;
import com.jie.blog.pojo.Comment;
import com.jie.blog.service.BlogService;
import com.jie.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return Result.success(save);
    }
}
