package com.example.softwareproject.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    //⽤⼾Id
    private Long userId;
    //⽤⼾名称
    private String username;
    //⽤⼾⼿机号
    @NotBlank(message = "Phone is mandatory")
    @Size(min = 11, max = 11, message = "Phone must be 11 characters long")
    private String phone;
    //⽤⼾头像
    private String avatar;
    //用户密码
    private String password;
    //用户token
    private String token;
    //用户登录状态
    private Boolean loginStatus;
}
