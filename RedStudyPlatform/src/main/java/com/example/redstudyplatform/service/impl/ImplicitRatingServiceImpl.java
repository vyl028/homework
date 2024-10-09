package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.ImplicitRatingDao;
import com.example.redstudyplatform.domain.ImplicitRating;
import com.example.redstudyplatform.service.ImplicitRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImplicitRatingServiceImpl implements ImplicitRatingService {

    @Autowired
    private ImplicitRatingDao implicitRatingDao;

    @Override
    public void addRating(ImplicitRating rating) {
        implicitRatingDao.insertImplicitRating(rating);
    }

    @Override
    public List<ImplicitRating> getRatingsByUserId(Integer stuid) {
        return implicitRatingDao.getRatingsByUserId(stuid);
    }

    @Override
    public List<ImplicitRating> getAllRatings() {
        return implicitRatingDao.getAllRatings();
    }
}
