package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.ReservationApprovalDTO;
import com.example.redstudyplatform.domain.ReservationInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationInfoService {
    void insertReservationInfo(ReservationInfo reservationInfo);

    void insertStudyArrangement(ReservationInfo reservationInfo);

    List<ReservationApprovalDTO> findAllReservationsWithApprovalByBaseAdminId(int baseAdminId);

    void assignGuide(int guideID, int studyId, int baseAdminId);

    void updateApprovalStatus(boolean isApprove, String reason, int studyId, int baseAdminId);

    ReservationInfo findReservationByUserIdAndBaseAdminId(int studyId, int baseAdminId);

    int countAll();

}
