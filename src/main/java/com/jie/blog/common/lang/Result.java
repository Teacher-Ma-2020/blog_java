package com.jie.blog.common.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        return successResult(200,"操作成功",data);
    }

    public static Result successResult(int code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result fail(String msg){
        return failResult(400,msg,null);
    }

    public static Result failResult(int code,String msg,Object data){
        return new Result(code,msg,data);
    }
}
