package com.example.softwareproject.mapper;

import com.example.softwareproject.pojo.WeiboInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WeiboMapper {
    @Select("select * from weiboinfo")
    List<WeiboInfo> get();

    @Select("select images from weiboinfo where weiboId=#{id}")
    String getImagesById(Long weiboId);

    @Select("select * from weiboinfo where weiboId=#{weiboId}")
    WeiboInfo getWeibo(Long weiboId);
    @Insert("insert into weiboinfo(userId,  title, videoUrl, poster, images, likeCount, likeFlag, category) "+
            "VALUES (#{userId},  #{title}, #{videoUrl}, #{poster}, #{imagesJson}, #{likeCount}, #{likeFlag}, #{category})")
    @Options(keyProperty = "weiboId",useGeneratedKeys = true)
    void insert(WeiboInfo weiboInfo);

    @Delete("delete from weiboinfo where weiboId=#{weiboId}")
    void delete(Long weiboId);

    @Update("UPDATE weiboinfo SET likeCount = likeCount + 1 WHERE weiboId = #{weiboId}")
    void addLikeCount(Long weiboId);
    @Update("UPDATE weiboinfo SET likeCount = CASE WHEN likeCount > 0 THEN likeCount - 1 ELSE 0 END WHERE weiboId = #{weiboId}")
    void decLikeCount(Long weiboId);

    @Select("select count(*)>0 from weiboinfo where weiboId=#{weiboId}")
    Boolean isExist(Long weiboId);

    @Select("select count(*)>0 from weiboinfo where weiboId=#{weiboId} and userId=#{userId}")
    Boolean isWeiboOfUser(Long weiboId,Long userId);
}
