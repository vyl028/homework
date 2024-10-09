package com.example.redstudyplatform.domain;

import lombok.Getter;

//个人中心/查看研学安排
public class UserReservationInfoDTO {
    @Getter
    private String arrangeTime;  // 预约时间
    private boolean isApprove;   // 审核状态
    private boolean isGuide;     // 是否需要讲解员
    @Getter
    private String baseName;     // 基地名称
    @Getter
    private String activityName; // 活动名称
    @Getter
    private String reason;       // 预约失败理由

    public String getArrangeTime() {
        return arrangeTime;
    }

    public void setArrangeTime(String arrangeTime) {
        this.arrangeTime = arrangeTime;
    }

    public boolean isApprove() {
        return isApprove;
    }

    public void setApprove(boolean approve) {
        isApprove = approve;
    }

    public boolean isGuide() {
        return isGuide;
    }

    public void setGuide(boolean guide) {
        isGuide = guide;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "UserReservationInfoDTO{" +
                "arrangeTime='" + arrangeTime + '\'' +
                ", isApprove=" + isApprove +
                ", isGuide=" + isGuide +
                ", baseName='" + baseName + '\'' +
                ", activityName='" + activityName + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
