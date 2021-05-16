package com.jie.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jie.blog.common.lang.Result;
import com.jie.blog.pojo.Blog;
import com.jie.blog.pojo.Blogs;
import com.jie.blog.service.BlogService;
import com.jie.blog.service.BlogsService;
import com.jie.blog.service.UserService;
import com.jie.blog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@RestController
@CrossOrigin
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    BlogsService blogsService;

    @Autowired
    UserService userService;


    /**
     * 查询所有博客
     * @param currentPage
     * @return
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1" ) Integer currentPage,String search,Boolean radio){
        IPage pageDate;
        Page page=new Page(currentPage,5);

        String orderBy=radio?"created":"view";
        if (Objects.equals(search, "")){
            pageDate = blogsService.page(page, new QueryWrapper<Blogs>().orderByDesc(orderBy));
            List records = pageDate.getRecords();
            for (Object o: records){
                Blogs temp = (Blogs) o;
                temp.setDynamicTags(temp.getTags().split(" "));
            }
        }else{
            pageDate = blogsService.page(page, new QueryWrapper<Blogs>().like("title",search).or().like("description",search).or().like("user_name",search).or().like("tags",search).orderByDesc(orderBy));
            List records = pageDate.getRecords();
            for (Object o: records){
                Blogs temp = (Blogs) o;
                temp.setDynamicTags(temp.getTags().split(" "));
            }
        }

        return Result.success(pageDate);
    }

    /**
     * 查询自己的博客
     * @param currentPage
     * @param userID
     * @return
     */
    @GetMapping("/blogSelf")
    public Result listSelf(@RequestParam(defaultValue = "1" ) Integer currentPage,Integer userID){
        Page page=new Page(currentPage,5);
        IPage pageDate = blogsService.page(page, new QueryWrapper<Blogs>().orderByDesc("created").eq("user_id",userID));
        return Result.success(pageDate);
    }

    @GetMapping("/blog/{id}")
    public Result list(@PathVariable(name ="id") Long id){
        Blog blog=blogService.getBlogById(id);
        Assert.notNull(blog,"该博客已被删除");
        blogService.addView(id,new Random().nextInt(3)+1);
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result list(@Validated @RequestBody Blog blog){
        Blog temp=null;
        //编辑
        if(blog.getId()!=null){
            temp=blogService.getById(blog.getId());
            temp.setId(blog.getId());
            temp.setCreated(LocalDateTime.now());
            Assert.isTrue(temp.getUserId().longValue()== ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }else{
            //添加
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setUserName(userService.getById(ShiroUtil.getProfile().getId()).getUsername());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created","status","userName","view");
        StringBuilder stringBuilder=new StringBuilder();
        for (String str:temp.getDynamicTags()){
            stringBuilder.append(str).append(" ");
        }
        temp.setTags(String.valueOf(stringBuilder));
        if(temp.getContent().length()>500){
            temp.setDescription(temp.getContent().substring(0,300));
        }else{
            temp.setDescription(temp.getContent());
        }
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }

    @RequiresAuthentication
    @GetMapping("/deleteBlog/{blogId}")
    public Result deleteBlog(@PathVariable("blogId") int blogId){
        boolean flag = blogService.removeById(blogId);
        return Result.success(flag);
    }

    /**
     * 获取热门文章
     */
    @GetMapping("/getHot")
    public Result getHot(){
        List<Blog> hot = blogService.getHot();
        return Result.success(hot);
    }

    @GetMapping("/getNum")
    public Result getHot(int userid){
        List<Blog> list = blogService.list(new QueryWrapper<Blog>().eq("user_id",userid));
        return Result.success(list.size());
    }

    @GetMapping("/hello")
    public Result hello(){
        System.out.println("master 冲突测试");
        return Result.success("hello");
    }
}
