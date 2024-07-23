package com.example.softwareproject.interceptor;

import com.example.softwareproject.pojo.Comment;
import com.example.softwareproject.pojo.WeiboInfo;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class KeywordInterceptor {

    // 定义关键词列表
    private static final List<String> KEYWORDS = Arrays.asList("关键词1", "关键词2", "关键词3");

    // 检查字符串是否包含任何关键词
    public boolean containsKeyword(String text) {
        if (text == null) {
            return false;
        }
        for (String keyword : KEYWORDS) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    // 检查WeiboInfo和它的评论
    public boolean containsKeywordInWeiboInfo(WeiboInfo weiboInfo) {
        if (weiboInfo.getTitle() != null) {
            return containsKeyword(weiboInfo.getTitle());
        }
        return false;
    }
    public boolean containsKeywordInComment(Comment comment){
        if (comment.getText() != null) {
            return containsKeyword(comment.getText());
        }
        return false;
    }
}
