package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    List<Feedback> findAllFeedbackByActivityID(int activityID);
    void addFeedback(Feedback feedback);
}
