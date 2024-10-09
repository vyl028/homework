package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.BaseInfoDao;
import com.example.redstudyplatform.domain.BaseInfoDTO;
import com.example.redstudyplatform.service.BaseInfoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseInfoDTOServiceImpl implements BaseInfoDTOService {
    @Autowired
    private BaseInfoDao baseInfoDao;

    @Override
    public List<BaseInfoDTO> findAllBaseActivitiesAndGuides() {
        return baseInfoDao.findAllBaseActivitiesAndGuides();
    }

    @Override
    public List<BaseInfoDTO> searchBaseByName(String search) {
        return baseInfoDao.searchBaseByName(search);
    }

    @Override
    public List<BaseInfoDTO> searchBaseByType(String type) {
        return baseInfoDao.searchBaseByType(type);
    }
}
