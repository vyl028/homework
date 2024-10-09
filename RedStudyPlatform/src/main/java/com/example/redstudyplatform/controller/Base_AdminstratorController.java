package com.example.redstudyplatform.controller;

import com.example.redstudyplatform.domain.Base_Administrator;
import com.example.redstudyplatform.domain.ReservationApprovalDTO;
import com.example.redstudyplatform.domain.ReservationInfo;
import com.example.redstudyplatform.domain.Result;
import com.example.redstudyplatform.service.Base_AdministratorService;
import com.example.redstudyplatform.service.ReservationInfoService;
import com.example.redstudyplatform.utils.JwtUtil;
import com.example.redstudyplatform.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/base")
@Validated
public class Base_AdminstratorController {
    @Qualifier("base_AdministratorServiceImpl")
    @Autowired
    private Base_AdministratorService baseAdministratorService;
    @Autowired
    private ReservationInfoService reservationInfoService;


    //用户登录
    @PostMapping("login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        Base_Administrator loginUser = baseAdministratorService.getByUsername(username);

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

    //订单处理/审核预约
    @PostMapping("reservationList")
    public Result<List<ReservationApprovalDTO>> showAllReservations(@RequestBody int baseAdminId) {
        List<ReservationApprovalDTO> reservationApprovalDTOS = reservationInfoService.findAllReservationsWithApprovalByBaseAdminId(baseAdminId);
        if (reservationApprovalDTOS == null || reservationApprovalDTOS.isEmpty()) {
            return Result.error("暂无数据");
        } else {
            return Result.success(reservationApprovalDTOS);
        }
    }

    //订单处理/审核预约（查看详情_查看）
    @PostMapping("reservationList/reservationInfo")
    public Result<ReservationInfo> showReservation(@RequestBody int studyId, @RequestBody int baseAdminId) {
        ReservationInfo reservationInfo = reservationInfoService.findReservationByUserIdAndBaseAdminId(studyId, baseAdminId);
        if (reservationInfo == null) {
            return Result.error("暂无数据");
        } else {
            return Result.success(reservationInfo);
        }
    }

    //订单处理/审核预约（查看详情_是否通过以及不通过原因）
    @PostMapping("reservationList/checkReservation")
    public Result<String> checkReservation(int guideID, int studyId, int baseAdminId, boolean isApprove, String reason) {
        reservationInfoService.assignGuide(guideID, studyId, baseAdminId);
        reservationInfoService.updateApprovalStatus(isApprove, reason, studyId, baseAdminId);
        return Result.success("成功");
    }

}
