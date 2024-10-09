package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.StudyDao;
import com.example.redstudyplatform.domain.Study;
import com.example.redstudyplatform.service.StudyService;
import com.example.redstudyplatform.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImpl implements StudyService {
    @Autowired
    private StudyDao studyDao;

    @Override
    public Study getByUsername(String username){
        Study study=studyDao.getByUsername(username);
        return study;
    }

    @Override
    public void register(String username, String password,String telephone,String company,String name,String location) {
        Study study = new Study();
        //加密
        String md5String= Md5Util.getMD5String(password);

        study.setUsername(username);
        study.setPassword(md5String);
        study.setTelephone(telephone);
        study.setCompany(company);
        study.setName(name);
        study.setLocation(location);

        //添加
        studyDao.add(study);
    }

    @Override
    public int countAll() {
        return studyDao.countAll();
    }
}
