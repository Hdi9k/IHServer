package com.example.softwareproject.service;

import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserInfo;
import com.example.softwareproject.pojo.UserInterest;
import com.example.softwareproject.pojo.WeiboInfo;

import java.util.List;

public interface UserService {
    UserInfo get(Long id);

    Result insert(UserInfo userInfo);

    List<WeiboInfo> getUserWeibo(Long id);

    UserInterest getUserInterest(Long id);

    UserInfo login(UserInfo userInfo);

    void logout(Long id);

    void setInterest(Integer category, Long id);

    Result delete(Long weiboId,Long userId);

    void update(UserInfo userInfo);
}
