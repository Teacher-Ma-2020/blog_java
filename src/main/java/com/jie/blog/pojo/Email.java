package com.jie.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("m_email")
public class Email {
  @TableId(value = "id",type = IdType.AUTO)
  private long id;
  private String email;
  private long content;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime time;

  public Email(String email, long content) {
    this.email = email;
    this.content = content;
    this.time = LocalDateTime.now();
  }
}
