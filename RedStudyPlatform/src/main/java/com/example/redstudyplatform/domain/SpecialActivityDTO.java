package com.example.redstudyplatform.domain;

import java.util.List;

//研学资源/查看资源（有误）
public class SpecialActivityDTO {
    private int activityID;
    private String activityName;
    private boolean auditStatus;
    private String introduction;
    private double rating;
    private String baseName; // 基地名称
    private List<Guide> guides; // 包含多个讲解员信息


    public static class Guide {
        private int guideID;
        private String name;
        private String contact;
        private String employeeID;
        private String joinTime;

        public int getGuideID() {
            return guideID;
        }

        public void setGuideID(int guideID) {
            this.guideID = guideID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(String employeeID) {
            this.employeeID = employeeID;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
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

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public List<Guide> getGuides() {
        return guides;
    }

    public void setGuides(List<Guide> guides) {
        this.guides = guides;
    }

    @Override
    public String toString() {
        return "SpecialActivityDTO{" +
                "activityID=" + activityID +
                ", activityName='" + activityName + '\'' +
                ", auditStatus=" + auditStatus +
                ", introduction='" + introduction + '\'' +
                ", rating=" + rating +
                ", baseName='" + baseName + '\'' +
                ", guides=" + guides +
                '}';
    }
}
