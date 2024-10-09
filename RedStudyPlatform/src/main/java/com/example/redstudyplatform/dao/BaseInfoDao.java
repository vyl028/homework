package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.BaseInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BaseInfoDao {

    // 查询所有基地及其对应的活动和讲解员信息
    @Select("SELECT ba.bauid AS baseID, ba.baname AS baseName, ba.bausername AS baseUsername, ba.barole AS baseRole, " +
            "ba.type AS baseType, ba.maximumNum AS baseMaximumNum, ba.service AS baseService, " +
            "sa.activityID AS activityID, sa.activityName AS activityName, sa.auditStatus AS auditStatus, " +
            "sa.introduction AS introduction, sa.rating AS rating, " +
            "g.guideID AS guideID, g.name AS guideName, g.contact AS guideContact, " +
            "g.employeeID AS guideEmployeeID, g.joinTime AS guideJoinTime " +
            "FROM base_administrator ba " +
            "LEFT JOIN specialactivity sa ON ba.bauid = sa.bauid " +
            "LEFT JOIN guide g ON ba.bauid = g.bauid")
    @Results({
            @Result(property = "baseID", column = "baseID"),
            @Result(property = "baseName", column = "baseName"),
            @Result(property = "baseUsername", column = "baseUsername"),
            @Result(property = "baseRole", column = "baseRole"),
            @Result(property = "type", column = "baseType"),
            @Result(property = "maximumNum", column = "baseMaximumNum"),
            @Result(property = "service", column = "baseService"),
            @Result(property = "activities.activityID", column = "activityID"),
            @Result(property = "activities.activityName", column = "activityName"),
            @Result(property = "activities.auditStatus", column = "auditStatus"),
            @Result(property = "activities.introduction", column = "introduction"),
            @Result(property = "activities.rating", column = "rating"),
            @Result(property = "guides.guideID", column = "guideID"),
            @Result(property = "guides.name", column = "guideName"),
            @Result(property = "guides.contact", column = "guideContact"),
            @Result(property = "guides.employeeID", column = "guideEmployeeID"),
            @Result(property = "guides.joinTime", column = "joinTime")
    })
    public List<BaseInfoDTO> findAllBaseActivitiesAndGuides();

    // 根据用户输入的字段搜索基地名并返回基地及其活动、讲解员的信息
    @Select("SELECT ba.bauid AS baseID, ba.baname AS baseName, ba.bausername AS baseUsername, ba.barole AS baseRole, " +
            "ba.type AS baseType, ba.maximumNum AS baseMaximumNum, ba.service AS baseService, " +
            "sa.activityID AS activityID, sa.activityName AS activityName, sa.auditStatus AS auditStatus, " +
            "sa.introduction AS introduction, sa.rating AS rating, " +
            "g.guideID AS guideID, g.name AS guideName, g.contact AS guideContact, " +
            "g.employeeID AS guideEmployeeID, g.joinTime AS guideJoinTime " +
            "FROM base_administrator ba " +
            "LEFT JOIN specialactivity sa ON ba.bauid = sa.bauid " +
            "LEFT JOIN guide g ON ba.bauid = g.bauid " +
            "WHERE ba.baname LIKE CONCAT('%', #{search}, '%')")
    @Results({
            @Result(property = "baseID", column = "baseID"),
            @Result(property = "baseName", column = "baseName"),
            @Result(property = "baseUsername", column = "baseUsername"),
            @Result(property = "baseRole", column = "baseRole"),
            @Result(property = "type", column = "baseType"),
            @Result(property = "maximumNum", column = "baseMaximumNum"),
            @Result(property = "service", column = "baseService"),
            @Result(property = "activities.activityID", column = "activityID"),
            @Result(property = "activities.activityName", column = "activityName"),
            @Result(property = "activities.auditStatus", column = "auditStatus"),
            @Result(property = "activities.introduction", column = "introduction"),
            @Result(property = "activities.rating", column = "rating"),
            @Result(property = "guides.guideID", column = "guideID"),
            @Result(property = "guides.name", column = "guideName"),
            @Result(property = "guides.contact", column = "guideContact"),
            @Result(property = "guides.employeeID", column = "employeeID"),
            @Result(property = "guides.joinTime", column = "joinTime")
    })
    public List<BaseInfoDTO> searchBaseByName(String search);

    // 根据基地类型搜索并返回基地及其活动、讲解员信息
    @Select("SELECT ba.bauid AS baseID, ba.baname AS baseName, ba.bausername AS baseUsername, ba.barole AS baseRole, " +
            "ba.type AS baseType, ba.maximumNum AS baseMaximumNum, ba.service AS baseService, " +
            "sa.activityID AS activityID, sa.activityName AS activityName, sa.auditStatus AS auditStatus, " +
            "sa.introduction AS introduction, sa.rating AS rating, " +
            "g.guideID AS guideID, g.name AS guideName, g.contact AS guideContact, " +
            "g.employeeID AS guideEmployeeID, g.joinTime AS guideJoinTime " +
            "FROM base_administrator ba " +
            "LEFT JOIN specialactivity sa ON ba.bauid = sa.bauid " +
            "LEFT JOIN guide g ON ba.bauid = g.bauid " +
            "WHERE ba.type LIKE CONCAT('%', #{type}, '%')")
    @Results({
            @Result(property = "baseID", column = "baseID"),
            @Result(property = "baseName", column = "baseName"),
            @Result(property = "baseUsername", column = "baseUsername"),
            @Result(property = "baseRole", column = "baseRole"),
            @Result(property = "type", column = "baseType"),
            @Result(property = "maximumNum", column = "baseMaximumNum"),
            @Result(property = "service", column = "baseService"),
            @Result(property = "activities.activityID", column = "activityID"),
            @Result(property = "activities.activityName", column = "activityName"),
            @Result(property = "activities.auditStatus", column = "auditStatus"),
            @Result(property = "activities.introduction", column = "introduction"),
            @Result(property = "activities.rating", column = "rating"),
            @Result(property = "guides.guideID", column = "guideID"),
            @Result(property = "guides.name", column = "guideName"),
            @Result(property = "guides.contact", column = "guideContact"),
            @Result(property = "guides.employeeID", column = "guideEmployeeID"),
            @Result(property = "guides.joinTime", column = "joinTime")
    })
    public List<BaseInfoDTO> searchBaseByType(String type);
}

