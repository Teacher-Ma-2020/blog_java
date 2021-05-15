package com.jie.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("m_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

  @TableId(value = "id",type = IdType.AUTO)
  private Long id;
  private String content;
  private Long userId;
  private Long blogId;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime created;
  @TableField(exist = false)
  private User user;

  public Comment(String content, long userId, long blogId) {
    this.content = content;
    this.userId = userId;
    this.blogId = blogId;
  }
}
