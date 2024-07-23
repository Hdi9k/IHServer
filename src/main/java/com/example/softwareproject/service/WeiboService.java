package com.example.softwareproject.service;

import com.example.softwareproject.pojo.PageBean;
import com.example.softwareproject.pojo.Result;
import com.example.softwareproject.pojo.WeiboInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface WeiboService {
    PageBean get(Integer page, Integer pageSize);
    PageBean getByUser(Integer page, Integer pageSize,Long userId);
    Result insert(WeiboInfo weiboInfo);
    void setLike(Long weiboId,Long userId);
    void cancleLike(Long weiboId,Long userId);

}
