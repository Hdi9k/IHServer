package com.example.softwareproject.service.Impl;

import com.example.softwareproject.interceptor.KeywordInterceptor;
import com.example.softwareproject.mapper.*;
import com.example.softwareproject.pojo.*;
import com.example.softwareproject.service.WeiboService;
import com.example.softwareproject.util.JsonUtil;
import com.example.softwareproject.util.WeiboInfoSorter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class WeiboServiceImpl implements WeiboService {
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserInterestMapper userInterestMapper;
    @Autowired
    private KeywordInterceptor keywordInterceptor;

    @Override
    public PageBean get(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<WeiboInfo> list = weiboMapper.get();
        for (WeiboInfo info : list) {
            //将数据库中的json转换为String数组
            String json = weiboMapper.getImagesById(info.getWeiboId());
            List<String> jsonList = JsonUtil.fromJsonToImages(json);
            info.setImages(jsonList);

            //设置评论数
            info.setCommentCount(commentMapper.getCommentCount(info.getWeiboId()));

            //设置用户名与头像
            info.setUsername(userMapper.getUser(info.getUserId()).getUsername());
            info.setAvatar(userMapper.getUser(info.getUserId()).getAvatar());
        }

        Page<WeiboInfo> p = (Page<WeiboInfo>) list;

        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public PageBean getByUser(Integer page, Integer pageSize, Long userId) {
        PageHelper.startPage(page, pageSize);
        List<WeiboInfo> list = weiboMapper.get();

        //将微博根据兴趣排序
        UserInterest userInterest = userInterestMapper.getUserInterest(userId);
        WeiboInfoSorter.sortWeiboInfosByUserInterest(list, userInterest);

        List<UserLike> userLikes = likeMapper.getLike(userId);
        for (WeiboInfo info : list){
            //设置评论数
            info.setCommentCount(commentMapper.getCommentCount(info.getWeiboId()));

            //设置用户名与头像
            info.setUsername(userMapper.getUser(info.getUserId()).getUsername());
            info.setAvatar(userMapper.getUser(info.getUserId()).getAvatar());

            //将数据库中的json转换为String数组
            String json = weiboMapper.getImagesById(info.getWeiboId());
            List<String> jsonList = JsonUtil.fromJsonToImages(json);
            info.setImages(jsonList);

            //向微博中添加用户点赞状态
            for(UserLike like:userLikes){
                if(Objects.equals(like.getWeiboId(), info.getWeiboId())){
                    info.setLikeFlag(true);
                    break;
                }
            }
        }

        Page<WeiboInfo> p = (Page<WeiboInfo>) list;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Result insert(WeiboInfo weiboInfo) {
        //关键词拦截
        if (keywordInterceptor.containsKeywordInWeiboInfo(weiboInfo)) {
            return Result.error("The WeiboInfo contains forbidden keywords.");
        }
        //设置头像与名称
        UserInfo userInfo = userMapper.getUser(weiboInfo.getUserId());
        weiboInfo.setUsername(userInfo.getUsername());
        weiboInfo.setAvatar(userInfo.getAvatar());
        //将图片数组转换为json
        String image_json = JsonUtil.toJson(weiboInfo.getImages());
        weiboInfo.setImagesJson(image_json);
        //设置点赞数，评论数
        weiboInfo.setLikeCount(0);
        weiboInfo.setLikeFlag(false);
        weiboInfo.setCommentCount(0);


        weiboMapper.insert(weiboInfo);
        return Result.success();
    }

    @Override
    public void setLike(Long weiboId, Long userId) {

    }

    @Override
    public void cancleLike(Long weiboId, Long userId) {

    }


}
