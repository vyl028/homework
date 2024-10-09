package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.SpecialActivityDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecialActivityDTOService {
    List<SpecialActivityDTO> findAllSpecialActivitiesWithGuides();
}
