package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.UserReservationInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserReservationService {
    //列出所有信息
    List<UserReservationInfoDTO> findAllReservationsByUserId(int studyId);

    //找出对应基地预约失败理由
    UserReservationInfoDTO findFailedReservationByBaseId(int studyId, int baseId);

    //取消预约
    void deleteAllReservationsByUserIdAndBaseId(int studyId, int baseId);
}
