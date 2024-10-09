package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.ImplicitRating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImplicitRatingDao {

    // 插入新的隐式评分
    @Insert("INSERT INTO implicit_rating (stuid, bauid, interaction_score) VALUES (#{stuid}, #{bauid}, #{interactionScore})")
    void insertImplicitRating(ImplicitRating rating);

    // 根据用户ID获取隐式评分
    @Select("SELECT * FROM implicit_rating WHERE stuid = #{stuid}")
    List<ImplicitRating> getRatingsByUserId(@Param("stuid") Integer stuid);

    // 获取所有隐式评分
    @Select("SELECT * FROM implicit_rating")
    List<ImplicitRating> getAllRatings();
}