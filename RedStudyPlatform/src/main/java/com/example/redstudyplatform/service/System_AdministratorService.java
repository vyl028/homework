package com.example.redstudyplatform.service;

import com.example.redstudyplatform.domain.System_Administrator;
import org.springframework.stereotype.Service;

@Service
public interface System_AdministratorService {

    //查询用户
    System_Administrator getByUsername(String username);

    //注册
    void register(String username, String password);

}
