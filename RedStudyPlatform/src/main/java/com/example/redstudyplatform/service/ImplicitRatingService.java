package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.ImplicitRating;

import java.util.List;

public interface ImplicitRatingService {

    // 添加隐式评分
    void addRating(ImplicitRating rating);

    // 根据用户ID获取隐式评分
    List<ImplicitRating> getRatingsByUserId(Integer stuid);

    // 获取所有隐式评分
    List<ImplicitRating> getAllRatings();
}
