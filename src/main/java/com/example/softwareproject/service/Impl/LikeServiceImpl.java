package com.example.softwareproject.service.Impl;

import com.example.softwareproject.mapper.LikeMapper;
import com.example.softwareproject.mapper.UserInterestMapper;
import com.example.softwareproject.mapper.WeiboMapper;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserLike;
import com.example.softwareproject.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private UserInterestMapper userInterestMapper;
    @Override
    public List<UserLike> getLike(Long userId) {
        return likeMapper.getLike(userId);
    }

    @Override
    public Result addLike(Long userId, Long weiboId) {
        if(likeMapper.isLike(userId,weiboId)){
            log.info("已点赞过"+weiboId);
            return Result.error("不能重复点赞");
        }
        userInterestMapper.updateInterest(weiboMapper.getWeibo(weiboId).getCategory(),userId);
        likeMapper.addLike(userId, weiboId);
        weiboMapper.addLikeCount(weiboId);
        log.info("新增点赞"+weiboId);
        return Result.success(true);
    }

    @Override
    public Result cancelLike(Long userId, Long weiboId) {
        if(likeMapper.isLike(userId,weiboId)){
            likeMapper.cancelLike(userId, weiboId);
            weiboMapper.decLikeCount(weiboId);
            userInterestMapper.cancelInterest(weiboMapper.getWeibo(weiboId).getCategory(),userId);
            return Result.success(true);
        }
        return Result.error("未点赞的微博");
    }
}
