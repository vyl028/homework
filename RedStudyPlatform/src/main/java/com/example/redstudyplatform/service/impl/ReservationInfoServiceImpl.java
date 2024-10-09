package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.ReservationInfoDao;
import com.example.redstudyplatform.dao.StudyArrangementDao;
import com.example.redstudyplatform.domain.ReservationApprovalDTO;
import com.example.redstudyplatform.domain.ReservationInfo;
import com.example.redstudyplatform.service.ReservationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {

    @Autowired
    private ReservationInfoDao reservationInfoDao;
    @Autowired
    private StudyArrangementDao studyArrangementDao;

    @Override
    @Transactional
    public void insertReservationInfo(ReservationInfo reservationInfo) {
        reservationInfoDao.insertReservationInfo(reservationInfo);
    }

    @Override
    public void insertStudyArrangement(ReservationInfo reservationInfo) {
        reservationInfoDao.insertStudyArrangement(reservationInfo);
    }

    @Override
    public List<ReservationApprovalDTO> findAllReservationsWithApprovalByBaseAdminId(int baseAdminId) {
        return reservationInfoDao.findAllReservationsWithApprovalByBaseAdminId(baseAdminId);
    }

    @Override
    public void assignGuide(int guideID, int studyId, int baseAdminId) {
        studyArrangementDao.assignGuide(guideID, studyId, baseAdminId);
    }

    @Override
    public void updateApprovalStatus(boolean isApprove, String reason, int studyId, int baseAdminId) {
        studyArrangementDao.updateApprovalStatus(isApprove, reason, studyId, baseAdminId);
    }

    @Override
    public ReservationInfo findReservationByUserIdAndBaseAdminId(int studyId, int baseAdminId) {
        return studyArrangementDao.findReservationByUserIdAndBaseAdminId(studyId, baseAdminId);
    }

    @Override
    public int countAll() {
        return studyArrangementDao.countAll();
    }
}