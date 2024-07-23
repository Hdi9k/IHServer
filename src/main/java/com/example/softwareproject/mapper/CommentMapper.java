package com.example.softwareproject.mapper;

import com.example.softwareproject.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where weiboId=#{weiboId}")
    List<Comment>getComment(Long weiboId);

    @Select("select count(*) from comment where weiboId=#{weiboId}")
    Integer getCommentCount(Long weiboId);

    @Insert("INSERT INTO comment (weiboId, userId, text) VALUES (#{weiboId}, #{userId}, #{text})")
    void addComment(Long weiboId,Long userId,String text);

}
