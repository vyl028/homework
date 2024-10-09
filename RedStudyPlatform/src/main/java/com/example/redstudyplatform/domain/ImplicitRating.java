package com.example.redstudyplatform.domain;

//隐式评分
public class ImplicitRating {
    private Integer stuid;     // 用户ID
    private Integer bauid;     // 基地ID
    private Integer interactionScore;  // 隐式反馈评分

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public Integer getBauid() {
        return bauid;
    }

    public void setBauid(Integer bauid) {
        this.bauid = bauid;
    }

    public Integer getInteractionScore() {
        return interactionScore;
    }

    public void setInteractionScore(Integer interactionScore) {
        this.interactionScore = interactionScore;
    }

    @Override
    public String toString() {
        return "ImplicitRating{" +
                "stuid=" + stuid +
                ", bauid=" + bauid +
                ", interactionScore=" + interactionScore +
                '}';
    }
}
