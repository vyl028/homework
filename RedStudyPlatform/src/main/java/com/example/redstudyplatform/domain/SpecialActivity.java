package com.example.redstudyplatform.domain;

//研学活动
public class SpecialActivity {
    private int activityID;//活动ID
    private Base_Administrator baseAdministrator;//活动所属基地
    private String activityName;//活动名
    private boolean auditStatus;//审核情况
    private String introduction;//简介
    private double rating;//基地评分(显示所有研学用户评分后的平均分)

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public Base_Administrator getBaseAdministrator() {
        return baseAdministrator;
    }

    public void setBaseAdministrator(Base_Administrator baseAdministrator) {
        this.baseAdministrator = baseAdministrator;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public boolean isAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(boolean auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "SpecialActivity{" +
                "activityID=" + activityID +
                ", baseAdministrator=" + baseAdministrator +
                ", activityName='" + activityName + '\'' +
                ", auditStatus=" + auditStatus +
                ", introduction='" + introduction + '\'' +
                ", rating=" + rating +
                '}';
    }
}
