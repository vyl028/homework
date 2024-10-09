package com.example.redstudyplatform.service;


import com.example.redstudyplatform.domain.Study;
import org.springframework.stereotype.Service;

@Service
public interface StudyService {

    //查询用户
    Study getByUsername(String username);

    //注册
    void register(String username, String password,String telephone,String company,String name,String location);

    int countAll();
}
