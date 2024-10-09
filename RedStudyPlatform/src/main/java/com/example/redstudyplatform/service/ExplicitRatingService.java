package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.ExplicitRating;

import java.util.List;

public interface ExplicitRatingService {

    // 添加显式评分
    void addRating(ExplicitRating rating);

    // 根据用户ID获取显式评分
    List<ExplicitRating> getRatingsByUserId(Integer stuid);

    // 获取所有显式评分
    List<ExplicitRating> getAllRatings();
}
