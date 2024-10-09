package com.example.redstudyplatform.controller;

import com.example.redstudyplatform.domain.Base_Administrator;
import com.example.redstudyplatform.domain.Result;
import com.example.redstudyplatform.domain.System_Administrator;
import com.example.redstudyplatform.service.*;
import com.example.redstudyplatform.utils.JwtUtil;
import com.example.redstudyplatform.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
@Validated
public class System_AdminstratorController {
    @Qualifier("system_AdministratorServiceImpl")
    @Autowired
    private System_AdministratorService systemAdministratorService;
    @Qualifier("base_AdministratorServiceImpl")
    @Autowired
    private Base_AdministratorService baseAdministratorService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private SpecialActivityService specialActivityService;
    @Autowired
    private ReservationInfoService reservationInfoService;

    //用户注册
    @PostMapping("register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        System_Administrator u = systemAdministratorService.getByUsername(username);
        if (u == null) {
            //没有占用，注册
            systemAdministratorService.register(username, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    //用户登录
    @PostMapping("login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        System_Administrator loginUser = systemAdministratorService.getByUsername(username);

        //判断用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid", loginUser.getUid());
            claims.put("username", loginUser.getUsername());
            claims.put("role", loginUser.getRole());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }
        return Result.error("密码错误");

    }

    //添加基地管理员用户
    @PostMapping("addBaseAdmin")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, String name) {
        //查询用户
        Base_Administrator u = baseAdministratorService.getByUsername(username);
        if (u == null) {
            //没有占用，注册
            baseAdministratorService.register(username, password, name);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    //统计
    @PostMapping("statistics")
    public Result<List<Integer>> statistic() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(studyService.countAll());
        integerList.add(baseAdministratorService.countAll());
        integerList.add(specialActivityService.countAll());
        integerList.add(reservationInfoService.countAll());

        return Result.success(integerList);
    }

}
