package com.example.softwareproject.service.Impl;

import com.example.softwareproject.interceptor.KeywordInterceptor;
import com.example.softwareproject.mapper.CommentMapper;
import com.example.softwareproject.mapper.UserMapper;
import com.example.softwareproject.pojo.Comment;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.UserInfo;
import com.example.softwareproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private KeywordInterceptor keywordInterceptor;

    @Override
    public List<Comment> getComment(Long weiboId) {
        List<Comment>list=commentMapper.getComment(weiboId);
        //添加用户名称与头像
        for(Comment c:list){
            UserInfo userInfo=userMapper.getUser(c.getUserId());
            c.setUsername(userInfo.getUsername());
            c.setAvatar(userInfo.getAvatar());
        }
        return list;
    }

    @Override
    public Integer getCommentCount(Long weiboId) {
        return commentMapper.getCommentCount(weiboId);
    }

    @Override
    public Result addComment(Long weiboId, Long userId, String text) {
        if (keywordInterceptor.containsKeyword(text)) {
            return Result.error("评论含有屏蔽词");
        }
        commentMapper.addComment(weiboId, userId, text);
        return Result.success();
    }
}
