package com.example.redstudyplatform.domain;

import lombok.Getter;

//预约信息
public class ReservationInfo {
    @Getter
    private int reservationID; // 预约ID
    @Getter
    private Study study; // 预约用户
    @Getter
    private String reservationTime; // 预约时间
    @Getter
    private String reservationSector; // 预约单位
    @Getter
    private int reservationNum; // 预约人数
    @Getter
    private SpecialActivity specialActivity; // 预约活动
    @Getter
    private Base_Administrator baseAdministrator; // 预约基地
    private boolean isGuide; // 是否需要讲解员
    @Getter
    private String content; // 备注

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getReservationSector() {
        return reservationSector;
    }

    public void setReservationSector(String reservationSector) {
        this.reservationSector = reservationSector;
    }

    public int getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }

    public SpecialActivity getSpecialActivity() {
        return specialActivity;
    }

    public void setSpecialActivity(SpecialActivity specialActivity) {
        this.specialActivity = specialActivity;
    }

    public Base_Administrator getBaseAdministrator() {
        return baseAdministrator;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReservationInfo{" +
                "reservationID=" + reservationID +
                ", study=" + study +
                ", reservationTime='" + reservationTime + '\'' +
                ", reservationSector='" + reservationSector + '\'' +
                ", reservationNum=" + reservationNum +
                ", specialActivity=" + specialActivity +
                ", baseAdministrator=" + baseAdministrator +
                ", isGuide=" + isGuide +
                ", content='" + content + '\'' +
                '}';
    }
}
