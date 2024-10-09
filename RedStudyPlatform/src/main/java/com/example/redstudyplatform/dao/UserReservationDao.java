package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.UserReservationInfoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserReservationDao {
    //个人中心/查看研学安排（信息列表）
    @Select("SELECT sa.arrangeTime AS arrangeTime, sa.isApprove AS isApprove, sa.isGuide AS isGuide, " +
            "ba.baname AS baseName, act.activityName AS activityName, sa.reason AS reason " +
            "FROM studyarrangement sa " +
            "JOIN base_administrator ba ON sa.bauid = ba.bauid " +
            "JOIN specialactivity act ON sa.activityID = act.activityID " +
            "WHERE sa.stuid = #{studyId}")
    @Results({
            @Result(property = "arrangeTime", column = "arrangeTime"),
            @Result(property = "isApprove", column = "isApprove"),
            @Result(property = "isGuide", column = "isGuide"),
            @Result(property = "baseName", column = "baseName"),
            @Result(property = "activityName", column = "activityName"),
            @Result(property = "reason", column = "reason")
    })
    List<UserReservationInfoDTO> findAllReservationsByUserId(int studyId);

    //个人中心/查看研学安排（查看预约失败原因）
    // 根据 studyId 和 baseId 查找唯一的预约失败记录
    @Select("SELECT sa.arrangeTime AS arrangeTime, sa.isApprove AS isApprove, sa.isGuide AS isGuide, " +
            "ba.baname AS baseName, act.activityName AS activityName, sa.reason AS reason " +
            "FROM studyarrangement sa " +
            "JOIN base_administrator ba ON sa.bauid = ba.bauid " +
            "JOIN specialactivity act ON sa.activityID = act.activityID " +
            "WHERE sa.stuid = #{studyId} AND sa.bauid = #{baseId} AND sa.reason IS NOT NULL")
    @Results({
            @Result(property = "arrangeTime", column = "arrangeTime"),
            @Result(property = "isApprove", column = "isApprove"),
            @Result(property = "isGuide", column = "isGuide"),
            @Result(property = "baseName", column = "baseName"),
            @Result(property = "activityName", column = "activityName"),
            @Result(property = "reason", column = "reason")
    })
    UserReservationInfoDTO findFailedReservationByBaseId(@Param("studyId") int studyId, @Param("baseId") int baseId);

    //个人中心/查看研学安排（取消预约）
    // 根据studyId和baseId删除所有预约信息
    @Delete("DELETE FROM studyarrangement WHERE stuid = #{studyId} AND bauid = #{baseId}")
    void deleteAllReservationsByUserIdAndBaseId(@Param("studyId") int studyId, @Param("baseId") int baseId);
}
