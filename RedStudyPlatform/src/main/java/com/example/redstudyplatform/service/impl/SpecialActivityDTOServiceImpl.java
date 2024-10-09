package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.SpecialActivityDTODao;
import com.example.redstudyplatform.domain.SpecialActivityDTO;
import com.example.redstudyplatform.service.SpecialActivityDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialActivityDTOServiceImpl implements SpecialActivityDTOService {
    @Autowired
    SpecialActivityDTODao specialActivityDTODao;

    @Override
    public List<SpecialActivityDTO> findAllSpecialActivitiesWithGuides() {
        List<SpecialActivityDTO> specialActivityDTOS = specialActivityDTODao.findAllSpecialActivitiesWithGuides();
        return specialActivityDTOS;
    }
}
