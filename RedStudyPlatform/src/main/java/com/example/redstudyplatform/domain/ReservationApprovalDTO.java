package com.example.redstudyplatform.domain;

import lombok.Getter;

//包含预约信息和审核状态
@Getter
public class ReservationApprovalDTO {
    private int reservationID; // 预约ID
    @lombok.Getter
    private Study study; // 预约用户
    @lombok.Getter
    private String reservationTime; // 预约时间
    @lombok.Getter
    private String reservationSector; // 预约单位
    @lombok.Getter
    private int reservationNum; // 预约人数
    @lombok.Getter
    private SpecialActivity specialActivity; // 预约活动
    @lombok.Getter
    private Base_Administrator baseAdministrator; // 预约基地
    private boolean isGuide; // 是否需要讲解员
    @lombok.Getter
    private String content; // 备注
    private boolean isApprove; // 是否批准

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public void setReservationSector(String reservationSector) {
        this.reservationSector = reservationSector;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }

    public void setSpecialActivity(SpecialActivity specialActivity) {
        this.specialActivity = specialActivity;
    }

    public void setBaseAdministrator(Base_Administrator baseAdministrator) {
        this.baseAdministrator = baseAdministrator;
    }

    public boolean isGuide() {
        return isGuide;
    }

    public void setGuide(boolean guide) {
        isGuide = guide;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    @Override
    public String toString() {
        return "ReservationApprovalDTO{" +
                "reservationID=" + reservationID +
                ", study=" + study +
                ", reservationTime='" + reservationTime + '\'' +
                ", reservationSector='" + reservationSector + '\'' +
                ", reservationNum=" + reservationNum +
                ", specialActivity=" + specialActivity +
                ", baseAdministrator=" + baseAdministrator +
                ", isGuide=" + isGuide +
                ", content='" + content + '\'' +
                ", isApprove=" + isApprove +
                '}';
    }
}
