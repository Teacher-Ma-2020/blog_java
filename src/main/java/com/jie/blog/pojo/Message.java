package com.jie.blog.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("m_message")
public class Message {

  private long id;
  private String content;
  private long userId;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime created;

  @TableField(exist = false)
  private User user;

  public Message(long userId,String content){
    System.out.println("构造方法");
    this.userId=userId;
    this.content=content;
  }
}
