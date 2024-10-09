package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.UserReservationDao;
import com.example.redstudyplatform.domain.UserReservationInfoDTO;
import com.example.redstudyplatform.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReservationServiceImpl implements UserReservationService {
    @Autowired
    private UserReservationDao userReservationDao;

    @Override
    public List<UserReservationInfoDTO> findAllReservationsByUserId(int studyId) {
        return userReservationDao.findAllReservationsByUserId(studyId);
    }

    @Override
    public UserReservationInfoDTO findFailedReservationByBaseId(int studyId, int baseId) {
        return userReservationDao.findFailedReservationByBaseId(studyId, baseId);
    }

    @Override
    public void deleteAllReservationsByUserIdAndBaseId(int studyId, int baseId) {
        userReservationDao.deleteAllReservationsByUserIdAndBaseId(studyId,baseId);
    }
}
