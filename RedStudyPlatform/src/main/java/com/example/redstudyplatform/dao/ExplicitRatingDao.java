package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.ExplicitRating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExplicitRatingDao {

    // 插入新的显式评分
    @Insert("INSERT INTO explicit_rating (stuid, bauid, rating) VALUES (#{stuid}, #{bauid}, #{rating})")
    void insertExplicitRating(ExplicitRating rating);

    // 根据用户ID获取显式评分
    @Select("SELECT * FROM explicit_rating WHERE stuid = #{stuid}")
    List<ExplicitRating> getRatingsByUserId(@Param("stuid") Integer stuid);

    // 获取所有显式评分
    @Select("SELECT * FROM explicit_rating")
    List<ExplicitRating> getAllRatings();
}
