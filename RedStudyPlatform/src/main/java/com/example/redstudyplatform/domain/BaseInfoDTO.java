package com.example.redstudyplatform.domain;

import java.util.List;

//研学资源/查看资源
public class BaseInfoDTO {
    private int baseID;
    private String baseName;
    private String baseUsername;
    private String baseRole;
    private String type;// 基地类型
    private int maximumNum;// 最大接待人数
    private String service;// 服务描述
    private List<SpecialActivity> activities;// 基地的所有活动
    private List<Guide> guides;// 基地的所有讲解员

    public int getBaseID() {
        return baseID;
    }

    public void setBaseID(int baseID) {
        this.baseID = baseID;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseUsername() {
        return baseUsername;
    }

    public void setBaseUsername(String baseUsername) {
        this.baseUsername = baseUsername;
    }

    public String getBaseRole() {
        return baseRole;
    }

    public void setBaseRole(String baseRole) {
        this.baseRole = baseRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaximumNum() {
        return maximumNum;
    }

    public void setMaximumNum(int maximumNum) {
        this.maximumNum = maximumNum;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<SpecialActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<SpecialActivity> activities) {
        this.activities = activities;
    }

    public List<Guide> getGuides() {
        return guides;
    }

    public void setGuides(List<Guide> guides) {
        this.guides = guides;
    }

    @Override
    public String toString() {
        return "BaseInfoDTO{" +
                "baseID=" + baseID +
                ", baseName='" + baseName + '\'' +
                ", baseUsername='" + baseUsername + '\'' +
                ", baseRole='" + baseRole + '\'' +
                ", type='" + type + '\'' +
                ", maximumNum=" + maximumNum +
                ", service='" + service + '\'' +
                ", activities=" + activities +
                ", guides=" + guides +
                '}';
    }
}
