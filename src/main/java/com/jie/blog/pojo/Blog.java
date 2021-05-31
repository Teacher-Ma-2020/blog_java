package com.jie.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @NotNull(message = "标题不能为空")
    @TableField(value = "title")
    private String title;

    private String description;

    @NotNull(message = "内容不能为空")
    private String content;

    private LocalDateTime created;

    private Integer status;

    private String avatar;

    private String userName;

    private int view=1;

    private int commentCount;

    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private String[] dynamicTags;

    private String tags;
}
