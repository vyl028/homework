package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.SpecialActivityDao;
import com.example.redstudyplatform.domain.SpecialActivity;
import com.example.redstudyplatform.service.SpecialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialActivityServiceImpl implements SpecialActivityService {
    @Autowired
    private SpecialActivityDao specialActivityDao;

    @Override
    public SpecialActivity getByActivityID(int activityID) {
        return specialActivityDao.getByActivityID(activityID);
    }

    @Override
    public int countAll() {
        return specialActivityDao.countAll();
    }
}
