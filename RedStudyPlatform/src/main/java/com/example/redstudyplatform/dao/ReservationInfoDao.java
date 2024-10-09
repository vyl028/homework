package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.ReservationApprovalDTO;
import com.example.redstudyplatform.domain.ReservationInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationInfoDao {

    // 插入新的预约信息记录
    // 插入新的预约信息记录，同时将相关信息插入到studyarrangement表中
    //研学资源/查看资源（预约）
    @Insert({
            "INSERT INTO reservationinfo (stuid, reservationTime, reservationSector, reservationNum, activityID, bauid, isGuide, comment) ",
            "VALUES (#{study.uid}, #{reservationTime}, #{reservationSector}, #{reservationNum}, #{specialActivity.activityID}, ",
            "#{baseAdministrator.uid}, #{isGuide}, #{content})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "reservationID")
    void insertReservationInfo(ReservationInfo reservationInfo);

    // 插入studyarrangement表记录
    //研学资源/查看资源（预约）
    @Insert({
            "INSERT INTO studyarrangement (stuid, arrangeTime, bauid, activityID) ",
            "VALUES (#{study.uid}, #{reservationTime}, #{baseAdministrator.uid}, #{specialActivity.activityID})"
    })
    void insertStudyArrangement(ReservationInfo reservationInfo);


    //查询所有与该基地管理员相关的预约信息以及是否批准
    //订单处理/审核预约
    @Select("SELECT ri.*, sa.isApprove, s.* " +
            "FROM ReservationInfo ri " +
            "JOIN studyarrangement sa ON ri.study.uid = sa.stuid " +
            "JOIN Study s ON ri.study.uid = s.uid " +  // 映射 Study 表
            "WHERE ri.baseAdministrator.uid = #{baseAdminId}")
    @Results({
            @Result(property = "reservationID", column = "reservationID"),
            @Result(property = "study.uid", column = "uid"),  // 映射 Study 的字段
            @Result(property = "study.name", column = "name"),
            @Result(property = "reservationTime", column = "reservationTime"),
            @Result(property = "reservationSector", column = "reservationSector"),
            @Result(property = "reservationNum", column = "reservationNum"),
            @Result(property = "specialActivity.activityID", column = "specialActivityID"),
            @Result(property = "specialActivity.name", column = "specialActivityName"),
            @Result(property = "baseAdministrator.uid", column = "baseAdministratorID"),
            @Result(property = "baseAdministrator.name", column = "baseAdministratorName"),
            @Result(property = "isGuide", column = "isGuide"),
            @Result(property = "content", column = "content"),
            @Result(property = "isApprove", column = "isApprove")
    })
    List<ReservationApprovalDTO> findAllReservationsWithApprovalByBaseAdminId(int baseAdminId);
}

