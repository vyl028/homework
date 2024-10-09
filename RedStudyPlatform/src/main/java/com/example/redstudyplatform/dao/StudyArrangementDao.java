package com.example.redstudyplatform.dao;

import com.example.redstudyplatform.domain.ReservationInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudyArrangementDao {

    // 订单处理/审核预约（查看详情）
    // 更新指定预约的讲解员ID
    @Update("UPDATE studyarrangement SET guideID = #{guideID} " +
            "WHERE stuid = #{studyId} AND bauid = #{baseAdminId}")
    void assignGuide(@Param("guideID") int guideID,
                     @Param("studyId") int studyId,
                     @Param("baseAdminId") int baseAdminId);

    // 订单处理/审核预约（查看详情）
    // 更新预约的批准状态和理由
    @Update("UPDATE studyarrangement SET isApprove = #{isApprove}, reason = #{reason} " +
            "WHERE stuid = #{studyId} AND bauid = #{baseAdminId}")
    void updateApprovalStatus(@Param("isApprove") boolean isApprove,
                              @Param("reason") String reason,
                              @Param("studyId") int studyId,
                              @Param("baseAdminId") int baseAdminId);

    // 订单处理/审核预约（查看详情）
    // 根据用户ID查询用户预约信息
    @Select("SELECT ri.* FROM ReservationInfo ri WHERE ri.study.uid = #{studyId} AND ri.baseAdministrator.uid = #{baseAdminId}")
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
            @Result(property = "content", column = "content")
    })
    ReservationInfo findReservationByUserIdAndBaseAdminId(@Param("studyId") int studyId,
                                                          @Param("baseAdminId") int baseAdminId);

    // 统计总数量
    @Select("select count(*) from studyarrangement")
    public int countAll();
}
