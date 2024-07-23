package com.example.softwareproject.service;

import com.example.softwareproject.pojo.Comment;
import com.example.softwareproject.pojo.Result;

import java.util.List;

public interface CommentService {
    List<Comment>getComment(Long weiboId);
    Integer getCommentCount(Long weiboId);
    Result addComment(Long weiboId, Long userId, String text);
}
