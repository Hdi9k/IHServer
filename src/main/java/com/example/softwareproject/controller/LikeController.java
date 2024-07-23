package com.example.softwareproject.controller;

import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weibo/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/up")
    public Result addLike(Long weiboId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return likeService.addLike(userId, weiboId);
    }
    @GetMapping("/cancel")
    public Result cancelLike(Long weiboId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return likeService.cancelLike(userId, weiboId);
    }
}
