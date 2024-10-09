package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.BaseInfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseInfoDTOService {
    List<BaseInfoDTO> findAllBaseActivitiesAndGuides();
    List<BaseInfoDTO> searchBaseByName(String search);
    List<BaseInfoDTO> searchBaseByType(String type);
}
