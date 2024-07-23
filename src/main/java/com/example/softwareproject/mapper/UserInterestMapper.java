package com.example.softwareproject.mapper;

import com.example.softwareproject.pojo.UserInterest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInterestMapper {
    @Select("select * from userinterest where userId=#{id}")
    UserInterest getUserInterest(Long id);
    void updateInterest(@Param("category") int category, @Param("userId") Long userId);

    void cancelInterest(@Param("category") int category, @Param("userId") Long userId);
}
