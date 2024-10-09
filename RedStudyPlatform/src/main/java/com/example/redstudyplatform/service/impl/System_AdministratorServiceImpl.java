package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.System_AdministratorDao;
import com.example.redstudyplatform.domain.System_Administrator;
import com.example.redstudyplatform.service.System_AdministratorService;
import com.example.redstudyplatform.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class System_AdministratorServiceImpl implements System_AdministratorService {
    @Autowired
    private System_AdministratorDao systemAdministratorDao;

    @Override
    public System_Administrator getByUsername(String username) {
        System_Administrator systemAdministrator = systemAdministratorDao.getByUsername(username);
        return systemAdministrator;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String= Md5Util.getMD5String(password);
        //添加
        systemAdministratorDao.add(username,md5String);
    }
}
