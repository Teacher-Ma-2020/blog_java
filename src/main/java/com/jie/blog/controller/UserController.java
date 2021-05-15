package com.jie.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jie.blog.common.lang.Result;
import com.jie.blog.pojo.User;
import com.jie.blog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/index")
    public Object index(){
        User user=userService.getById(1L);
        return Result.success(user);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        String password = user.getPassword();
        Md5Hash md5Hash=new Md5Hash(password);
        user.setPassword(md5Hash.toHex());
        user.setStatus(0);
        userService.save(user);
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result update(@Validated @RequestBody User user){
        if (user.getPassword().length()<30){
            String password = user.getPassword();
            Md5Hash md5Hash=new Md5Hash(password);
            user.setPassword(md5Hash.toHex());
        }
        userService.updateById(user);
        return Result.success(user);
    }

    @GetMapping("/findName/{userId}")
    public Result findName(@PathVariable("userId") String userID){
        User user = userService.getById(userID);
        return Result.success(user);
    }

    /**
     * 判断是否账户重命名
     */
    @GetMapping("/name/{name}")
    public Result name(@PathVariable("name") String name){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",name);
        User user = userService.getOne(queryWrapper);
//        System.out.println(user);
        return Result.success(user);
    }

    /**
     * 判断是否邮箱重命名
     */
    @GetMapping("/email/{email}")
    public Result email(@PathVariable("email") String email){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User user = userService.getOne(queryWrapper);
//        System.out.println(user);
        return Result.success(user);
    }

    @GetMapping("/getAll")
    public Result getAll(){
        return Result.success(userService.getAll());
    }
}
