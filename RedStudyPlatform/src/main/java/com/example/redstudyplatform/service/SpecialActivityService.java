package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.SpecialActivity;
import org.springframework.stereotype.Service;

@Service
public interface SpecialActivityService {
    SpecialActivity getByActivityID(int activityID);

    int countAll();
}
