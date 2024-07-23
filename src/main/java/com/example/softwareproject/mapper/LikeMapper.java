package com.example.softwareproject.mapper;

import com.example.softwareproject.pojo.UserLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeMapper {
    @Select("select * from userlike where userId=#{userId}")
    List<UserLike> getLike(Long userId);

    @Select("select count(*)>0 from userlike where userId=#{userId} and weiboId=#{weiboId}")
    Boolean isLike(Long userId, Long weiboId);

    @Insert("insert into userlike(userId, weiboId) " +
            "values (#{userId},#{weiboId})")
    void addLike(Long userId, Long weiboId);

    @Delete("delete from userlike where userId=#{userId} and weiboId=#{weiboId}")
    void cancelLike(Long userId, Long weiboId);

    @Delete("delete from userlike where weiboId=#{weiboId}")
    void clearLike(Long weiboId);
}
