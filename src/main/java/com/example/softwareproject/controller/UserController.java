package com.example.softwareproject.controller;


import com.example.softwareproject.pojo.PageBean;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserInfo;
import com.example.softwareproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/weibo/user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/id")
    public Result getUserById(HttpServletRequest request){
        Long userId= (Long) request.getAttribute("userId");
        UserInfo userInfo =userService.get(userId);
        return Result.success(userInfo);
    }

    @PostMapping("/add")
    public Result addUser(@Valid @RequestBody UserInfo userInfo){//@Valid用于验证手机号长度
        userService.insert(userInfo);
        return Result.success();
    }
    @GetMapping("/weibo/id")
    public Result getUserWeibo(HttpServletRequest request){
        Long userId= (Long) request.getAttribute("userId");
        return Result.success(userService.getUserWeibo(userId));
    }

    @DeleteMapping("/weibo/id")
    public Result delUserWeibo(Long weiboId,HttpServletRequest request){
        Long userId= (Long) request.getAttribute("userId");
        return userService.delete(weiboId,userId);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserInfo userInfo,HttpServletRequest request){
        userInfo.setUserId((Long) request.getAttribute("userId"));
        userService.update(userInfo);
        return Result.success();
    }
}
