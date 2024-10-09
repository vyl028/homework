package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.ExplicitRatingDao;
import com.example.redstudyplatform.domain.ExplicitRating;
import com.example.redstudyplatform.service.ExplicitRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExplicitRatingServiceImpl implements ExplicitRatingService {

    @Autowired
    private ExplicitRatingDao explicitRatingDao;

    @Override
    public void addRating(ExplicitRating rating) {
        explicitRatingDao.insertExplicitRating(rating);
    }

    @Override
    public List<ExplicitRating> getRatingsByUserId(Integer stuid) {
        return explicitRatingDao.getRatingsByUserId(stuid);
    }

    @Override
    public List<ExplicitRating> getAllRatings() {
        return explicitRatingDao.getAllRatings();
    }
}
