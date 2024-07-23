package com.example.softwareproject.controller;


import com.example.softwareproject.pojo.PageBean;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserInterest;
import com.example.softwareproject.pojo.WeiboInfo;
import com.example.softwareproject.service.S3Service;
import com.example.softwareproject.service.UserService;
import com.example.softwareproject.service.WeiboService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequestMapping("/weibo/info")
@RestController
@CrossOrigin
public class WeiboController {
    @Autowired
    private WeiboService weiboService;
    @Autowired
    private UserService userService;
    @Autowired
    private S3Service s3Service;

    @GetMapping("/homePage")
    public Result getInfo(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          HttpServletRequest request) {
        if ((Boolean) request.getAttribute("isLogin")) {
            Long userId = (Long) request.getAttribute("userId");
            log.info("already login:" + userId);
            PageBean pageBean = weiboService.getByUser(page, pageSize, userId);
            return Result.success(pageBean);
        } else {
            log.info("not login");
            PageBean pageBean = weiboService.get(page, pageSize);
            return Result.success(pageBean);
        }

    }

    @GetMapping("/id")
    public Result getInfoByUser(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        PageBean pageBean = weiboService.getByUser(page, pageSize, userId);
        return Result.success(pageBean);
    }

    @GetMapping("/interest")
    public Result setInterest(Integer category, HttpServletRequest request) {
        if (category == null || category < 0 || category > 6) {
            return Result.error("微博种类数值错误");
        }
        Long userId = (Long) request.getAttribute("userId");
        userService.setInterest(category, userId);
        return Result.success();
    }

    @PostMapping("/add")
    public Result addWeiboInfo(@RequestBody WeiboInfo weiboInfo, HttpServletRequest request) {
        //log.info("添加微博");
        //log.info("添加"+weiboInfo.toString());

        Long userId = (Long) request.getAttribute("userId");
        weiboInfo.setUserId(userId);
        return weiboService.insert(weiboInfo);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        return s3Service.uploadImg(file);
    }
}
