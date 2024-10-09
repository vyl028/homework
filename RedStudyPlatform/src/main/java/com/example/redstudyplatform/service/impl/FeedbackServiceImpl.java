package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.FeedbackDao;
import com.example.redstudyplatform.domain.Feedback;
import com.example.redstudyplatform.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> findAllFeedbackByActivityID(int activityID) {
        return feedbackDao.findAllFeedbackByActivityID(activityID);
    }

    @Override
    public void addFeedback(Feedback feedback) {

    }
}
