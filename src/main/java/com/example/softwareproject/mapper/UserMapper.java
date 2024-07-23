package com.example.softwareproject.mapper;

import com.example.softwareproject.pojo.UserInfo;
import com.example.softwareproject.pojo.WeiboInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from userinfo where userId=#{id}")
    UserInfo getUser(Long id);

    @Select("select * from weiboinfo where userId=#{id} order by createTime desc")
    List<WeiboInfo> getUserWeibo(Long id);

    @Select("select count(*)>0 from userinfo where phone=#{phone}")
    Boolean isPhone(String phone);

    @Insert("INSERT INTO userinfo (username, phone, avatar, password, loginStatus) " +
            "VALUES (#{username}, #{phone}, #{avatar}, #{password}, #{loginStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insertUser(UserInfo userInfo);

    @Select("select * from userinfo where phone=#{phone} and password=#{password}")
    UserInfo getByPhoneAndPassword(UserInfo userInfo);

    @Update("UPDATE userinfo SET loginStatus = #{loginStatus} WHERE userId = #{userId}")
    void updateLoginStatus(@Param("userId") Long userId, @Param("loginStatus") Boolean loginStatus);

    void updateUserInfo(UserInfo userInfo);

}
