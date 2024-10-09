package com.example.redstudyplatform.service.impl;

import com.example.redstudyplatform.dao.Base_AdministratorDao;
import com.example.redstudyplatform.domain.Base_Administrator;
import com.example.redstudyplatform.service.Base_AdministratorService;
import com.example.redstudyplatform.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Base_AdministratorServiceImpl implements Base_AdministratorService {
    @Autowired
    private Base_AdministratorDao baseAdministratorDao;

    @Override
    public Base_Administrator getByUsername(String username) {
        Base_Administrator baseAdministrator = baseAdministratorDao.getByUsername(username);
        return baseAdministrator;
    }

    @Override
    public void register(String username, String password,String name) {
        Base_Administrator baseAdministrator = new Base_Administrator();
        //加密
        String md5String= Md5Util.getMD5String(password);
        baseAdministrator.setUsername(username);
        baseAdministrator.setPassword(md5String);
        baseAdministrator.setName(name);
        //添加
        baseAdministratorDao.add(baseAdministrator);
    }

    @Override
    public int countAll() {
        return baseAdministratorDao.countAll();
    }
}
