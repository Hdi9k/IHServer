package com.example.softwareproject.mapper;

import com.example.softwareproject.pojo.UserInterest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInterestMapper {
    @Select("select * from userinterest where userId=#{id}")
    UserInterest getUserInterest(Long id);

    @Insert("insert into userinterest (userId, news, entertainment, game, economics, digital, daily) " +
            "VALUES (#{userId}, #{userInterest.news}, #{userInterest.entertainment}, #{userInterest.game}, " +
            "#{userInterest.economics}, #{userInterest.digital}, #{userInterest.daily})")
    void insertUserInter(@Param("userId") Long userId, @Param("userInterest") UserInterest userInterest);


    void updateInterest(@Param("category") int category, @Param("userId") Long userId);

    void cancelInterest(@Param("category") int category, @Param("userId") Long userId);
}
