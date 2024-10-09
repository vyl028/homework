package com.example.redstudyplatform.domain;

//显式评分
public class ExplicitRating {
    private Integer stuid;   // 用户ID
    private Integer bauid;   // 基地ID
    private Integer rating;  // 显式评分

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ExplicitRating{" +
                "stuid=" + stuid +
                ", bauid=" + bauid +
                ", rating=" + rating +
                '}';
    }
}
