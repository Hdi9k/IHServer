package com.example.softwareproject.controller;

import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserInfo;
import com.example.softwareproject.service.UserService;
import com.example.softwareproject.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/weibo/login")
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserInfo userInfo){
        UserInfo userInfo1=userService.login(userInfo);
        if(userInfo1!=null){
            Map<String,Object>claims=new HashMap<>();
            claims.put("userId",userInfo1.getUserId());
            claims.put("phone",userInfo1.getPhone());
            String token=JwtUtil.generateJwt(claims);
            return Result.success(token);
        }

        return Result.error("手机号或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserInfo userInfo){
        return userService.insert(userInfo);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        Long userId= (Long) request.getAttribute("userId");
        userService.logout(userId);
        return Result.success();
    }
}
