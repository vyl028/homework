package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.SpecialActivityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SpecialActivityDTODao {

    // 查询所有 SpecialActivity 及其对应基地下的 Guide 信息以及基地名称
    @Select("SELECT sa.activityID AS activityID, sa.activityName AS activityName, sa.auditStatus AS auditStatus, " +
            "sa.introduction AS introduction, sa.rating AS rating, " +
            "ba.baname AS baseName, " + // 基地名称
            "g.guideID AS guideID, g.name AS guideName, g.contact AS guideContact, " +
            "g.employeeID AS guideEmployeeID, g.joinTime AS guideJoinTime " +
            "FROM specialactivity sa " +
            "JOIN base_administrator ba ON sa.bauid = ba.bauid " +
            "JOIN guide g ON g.bauid = ba.bauid")
    @Results({
            @Result(property = "activityID", column = "activityID"),
            @Result(property = "activityName", column = "activityName"),
            @Result(property = "auditStatus", column = "auditStatus"),
            @Result(property = "introduction", column = "introduction"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "baseName", column = "baseName"), // 映射基地名称
            @Result(property = "guides.guideID", column = "guideID"),
            @Result(property = "guides.name", column = "guideName"),
            @Result(property = "guides.contact", column = "guideContact"),
            @Result(property = "guides.employeeID", column = "guideEmployeeID"),
            @Result(property = "guides.joinTime", column = "guideJoinTime")
    })
    public List<SpecialActivityDTO> findAllSpecialActivitiesWithGuides();
}