package com.jie.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.blog.common.lang.Result;
import com.jie.blog.pojo.Email;
import com.jie.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;


    @Autowired
    JavaMailSender mailSender;
    /**
     * 获取验证码
     * @param email
     */
    @GetMapping("/get")
    public Result get(String email){
        System.out.println(email);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("验证码");
        long content = (long) ((Math.random() * 9 + 1) * 100000);
        String str="[博客-风的季节]验证码："+content+"，您正在创建博客/修改账号，请于15分钟内完成验证，若非本人操作，请忽略此短信。";
        mailMessage.setText(str);
        mailMessage.setFrom("319991012@qq.com");
        mailMessage.setTo(email);

        boolean isSuccess=true;
        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            isSuccess=false;
            throw  new RuntimeException("发送失败,请确认您的邮箱是否正确！");
        }finally {
            if (isSuccess){
                Email _email = new Email(email, content);
                _email.setTime(LocalDateTime.now().minusMinutes(-15));
                System.out.println(_email);
                emailService.saveOrUpdate(_email,new QueryWrapper<Email>().eq("email",email));
            }
            return Result.success(isSuccess);
        }
    }

    /**
     * 判断验证码是否正确
     */
    @GetMapping("/check/{email}/{content}")
    public Result check(@PathVariable("email") String email,@PathVariable("content") String content_s){
        Email _email = emailService.getOne(new QueryWrapper<Email>().eq("email", email));
        long content= 0;
        try {
            content = Long.parseLong(content_s);
        } catch (NumberFormatException e) {
            return Result.success(false);
        }

        if (_email==null){
            return Result.success(false);
        }

        boolean flag = _email.getContent() == content ;
        boolean flag2 = LocalDateTime.now().isBefore(_email.getTime());
        System.out.println(flag+" "+flag2);
        if (!flag2){
            emailService.remove(new QueryWrapper<Email>().eq("email", email));
        }
        if(flag && flag2){
            emailService.remove(new QueryWrapper<Email>().eq("email", email));
            return Result.success(true);
        }else{
            return Result.success(false);
        }
    }
}
