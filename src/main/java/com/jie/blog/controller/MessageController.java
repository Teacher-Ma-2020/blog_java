package com.jie.blog.controller;

import com.jie.blog.common.dto.MessAgeDto;
import com.jie.blog.common.lang.Result;
import com.jie.blog.pojo.Message;
import com.jie.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ResponseBody
@Controller
@RequestMapping("/message")
@CrossOrigin
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/getAll")
    public Result getAll(){
        return Result.success(messageService.getAlls());
    }


    @GetMapping("/deleteById")
    public Result deleteByIdl(Long id){
        return Result.success(messageService.removeById(id));
    }

    @PostMapping("/addMessage")
    public Result addMessage(@RequestBody MessAgeDto messAgeDto){
        if (messAgeDto.getUser_id()==0){
            throw new RuntimeException("请先登陆");
        }
        Message message=new Message(messAgeDto.getUser_id(),messAgeDto.getContent());
        message.setCreated(LocalDateTime.now());
        boolean save = messageService.save(message);
        return Result.success(save);
    }
}
