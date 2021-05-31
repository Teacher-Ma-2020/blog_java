package com.jie.blog.controller;

import com.jie.blog.common.lang.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    @PostMapping(value = "/upload")
    public Result upload(@RequestParam(value ="file", required = false)  MultipartFile file){
        String name="";
        if (file!=null){
            name=file.getSize()+"_"+file.getOriginalFilename();
            try {
                file.transferTo(new File("/work/tomcat/webapps/img/"+name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.success(name);
    }
}
