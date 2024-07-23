package com.example.softwareproject.service;

import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserLike;

import java.util.List;

public interface LikeService {
    List<UserLike>getLike(Long userId);
    Result addLike(Long userId, Long weiboId);
    Result cancelLike(Long userId,Long weiboId);
}
