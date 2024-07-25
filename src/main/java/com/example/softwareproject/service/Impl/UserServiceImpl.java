package com.example.softwareproject.service.Impl;

import com.example.softwareproject.mapper.*;
import com.example.softwareproject.pojo.*;
import com.example.softwareproject.service.UserService;
import com.example.softwareproject.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private UserInterestMapper userInterestMapper;

    @Override
    public UserInfo get(Long id) {
        return userMapper.getUser(id);
    }

    @Override
    public Result insert(UserInfo userInfo) {
        if(userMapper.isPhone(userInfo.getPhone())){
            return Result.error("手机号已注册");
        }
        if(userInfo.getAvatar()==null)userInfo.setAvatar("http://dummyimage.com/100x100");
        if(userInfo.getUsername()==null)userInfo.setUsername("用户" + (new Random().nextInt(90000) + 10000));
        if(userInfo.getLoginStatus()==null)userInfo.setLoginStatus(false);
        userMapper.insertUser(userInfo);
        UserInterest userInterest=new UserInterest();
        userInterestMapper.insertUserInter(userInfo.getUserId(),userInterest);
        return Result.success();
    }

    @Override
    public List<WeiboInfo> getUserWeibo(Long id) {
        List<WeiboInfo>list=userMapper.getUserWeibo(id);
        List<UserLike> userLikes = likeMapper.getLike(id);
        for (WeiboInfo info : list) {
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

        return list;
    }

    @Override
    public UserInterest getUserInterest(Long id) {
        return userInterestMapper.getUserInterest(id);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        UserInfo userInfo1 = userMapper.getByPhoneAndPassword(userInfo);
        if (userInfo1 != null) {
            userMapper.updateLoginStatus(userInfo1.getUserId(), true);
        }
        return userInfo1;
    }

    @Override
    public void logout(Long id) {
        userMapper.updateLoginStatus(id, false);
    }

    @Override
    public void setInterest(Integer category, Long id) {
        userInterestMapper.updateInterest(category, id);
    }

    @Override
    public Result delete(Long weiboId,Long userId) {
        if(weiboMapper.isWeiboOfUser(weiboId, userId)){
            weiboMapper.delete(weiboId);
            return Result.success();
        }else {
            return Result.error("帖子只能被发帖人删除");
        }
    }

    @Override
    public void update(UserInfo userInfo) {
        userMapper.updateUserInfo(userInfo);
    }
}
