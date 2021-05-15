package com.jie.blog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @NotNull(message = "昵称不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;
}
