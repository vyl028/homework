package com.example.redstudyplatform.controller;

import com.example.redstudyplatform.domain.*;
import com.example.redstudyplatform.service.*;
import com.example.redstudyplatform.utils.JwtUtil;
import com.example.redstudyplatform.utils.Md5Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/study")
@Validated
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired
    private BaseInfoDTOService baseInfoDTOService;
    @Autowired
    private SpecialActivityDTOService specialActivityDTOService;
    @Autowired
    private SpecialActivityService specialActivityService;
    @Autowired
    private ReservationInfoService reservationInfoService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserReservationService userReservationService;
    @Autowired
    private ExplicitRatingService explicitRatingService;
    @Autowired
    private ImplicitRatingService implicitRatingService;

    //用户注册
    @PostMapping("register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, String telephone, String company, String name, String location) {
        //查询用户
        Study u = studyService.getByUsername(username);
        if (u == null) {
            //没有占用，注册
            studyService.register(username, password, telephone, company, name, location);
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
        Study loginUser = studyService.getByUsername(username);

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

            List<String> dataList = Arrays.asList(loginUser.getUid().toString(), token);

            return Result.success(String.join("，", dataList));
        }
        return Result.error("密码错误");

    }

    //研学资源/查看资源（主页列表）
    @PostMapping("resource/baseList")
    public Result<List<BaseInfoDTO>> showActivityList() {
        List<BaseInfoDTO> baseInfoDTOS = baseInfoDTOService.findAllBaseActivitiesAndGuides();
        if (baseInfoDTOS == null || baseInfoDTOS.isEmpty()) {
            return Result.error("暂无数据");
        } else {
            return Result.success(baseInfoDTOS);
        }
    }

    //研学资源/查看资源（搜索）
    @PostMapping("resource/baseList/search")
    public Result<List<BaseInfoDTO>> searchBaseByName(String search) {
        List<BaseInfoDTO> baseInfoDTOS = baseInfoDTOService.searchBaseByName(search);
        if (baseInfoDTOS == null || baseInfoDTOS.isEmpty()) {
            return Result.error("暂无数据");
        } else {
            return Result.success(baseInfoDTOS);
        }
    }

    //研学资源/查看资源（查看对应某个活动信息）
    @PostMapping("resource/baseList/activityInfo")
    public Result<SpecialActivity> showActivityInfo(@RequestBody int activityID) {
        SpecialActivity specialActivity = specialActivityService.getByActivityID(activityID);
        if (specialActivity == null)
            return Result.error("暂无数据");
        else
            return Result.success(specialActivity);
    }

    //研学资源/查看资源（查看详情按钮）
    @PostMapping("resource/baseList/activityContent")
    public Result<String> showActivityContent(@RequestBody int activityID) {
        SpecialActivity specialActivity = specialActivityService.getByActivityID(activityID);
        if (specialActivity == null)
            return Result.error("暂无数据");
        else
            return Result.success(specialActivity.getIntroduction());
    }

    //研学资源/查看资源（预约）
    @PostMapping("resource/baseList/reservation")
    public Result<String> saveReservationInfo(@RequestBody ReservationInfo reservationInfo) {
        try {
            reservationInfoService.insertReservationInfo(reservationInfo);
            reservationInfoService.insertStudyArrangement(reservationInfo);
            return Result.success("保存成功");
        } catch (Exception e) {
            return Result.error("保存失败");
        }
    }

    //研学资源/查看资源（筛选（根据基地类型筛选））
    @PostMapping("resource/baseList/searchByType")
    public Result<List<BaseInfoDTO>> searchBaseByType(@RequestBody String type) {
        List<BaseInfoDTO> baseInfoDTOS = baseInfoDTOService.searchBaseByType(type);
        if (baseInfoDTOS == null || baseInfoDTOS.isEmpty()) {
            return Result.error("暂无数据");
        } else {
            return Result.success(baseInfoDTOS);
        }
    }

    //研学资源/查看资源（查看评价）
    @PostMapping("resource/baseList/feedbackList")
    public Result<List<Feedback>> showFeedbackList(@RequestBody int activutyID) {
        List<Feedback> feedbacks = feedbackService.findAllFeedbackByActivityID(activutyID);
        if (feedbacks == null || feedbacks.isEmpty()) {
            return Result.error("暂无数据");
        } else {
            return Result.success(feedbacks);
        }
    }

    //研学资源/查看资源（查看评价-写评价）
    @PostMapping("resource/baseList/feedbackList/addFeedback")
    public Result<String> addFeedback(@RequestBody Feedback feedback) {
        feedbackService.addFeedback(feedback);
        return Result.success("评论已成功发布");
    }

    //个人中心/查看研学安排
    @PostMapping("individual/arrangeList")
    public Result<List<UserReservationInfoDTO>> showUserReservationsList(@RequestBody int studyId) {
        List<UserReservationInfoDTO> userReservationInfoDTOS = userReservationService.findAllReservationsByUserId(studyId);
        if (userReservationInfoDTOS == null || userReservationInfoDTOS.isEmpty()) {
            return Result.error("暂无数据");
        } else {
            return Result.success(userReservationInfoDTOS);
        }
    }

    //个人中心/查看研学安排（查看预约失败原因）
    @PostMapping("individual/arrangeList/reservationFailure")
    public Result<UserReservationInfoDTO> showReservationFailureReason(@RequestBody int studyId, @RequestBody int baseId) {
        UserReservationInfoDTO userReservationInfoDTO = userReservationService.findFailedReservationByBaseId(studyId, baseId);
        if (userReservationInfoDTO == null) {
            return Result.error("暂无数据");
        } else {
            return Result.success(userReservationInfoDTO);
        }
    }

    //个人中心/查看研学安排（取消预约）
    @DeleteMapping("individual/arrangeList/{studyId}/{baseId}")
    public  Result<String> deleteAllReservationsByUserIdAndBaseId(@PathVariable("studyId") int studyId, @PathVariable("baseId") int baseId){
        userReservationService.deleteAllReservationsByUserIdAndBaseId(studyId,baseId);
        return Result.success("成功取消预约");
    }

    //个性化推荐
    @PostMapping("/recommendations")
    public String runPythonScript(int id) {
        try {
            // 获取数据库中的显式评分和隐式评分
            List<ExplicitRating> explicitRatings = explicitRatingService.getAllRatings();
            List<ImplicitRating> implicitRatings = implicitRatingService.getAllRatings();

            // 将评分数据转为 Python 脚本需要的 JSON 格式
            String explicitData = new ObjectMapper().writeValueAsString(explicitRatings);
            String implicitData = new ObjectMapper().writeValueAsString(implicitRatings);

            ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\YChen\\Desktop\\Study\\Project\\Work\\RedStudyPlatform\\pythonProject\\script.py");
            Process process = processBuilder.start();

            OutputStream os = process.getOutputStream();
            PrintWriter writer = new PrintWriter(os);

            // 向 Python 传递数据
            writer.println("{\"name\":\"" + String.valueOf(id) + "\", \"explicit_feedback\": " + explicitData + ", \"implicit_feedback\": " + implicitData + "}");
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line);
            }

            process.waitFor();

            if (errorOutput.length() > 0) {
                System.err.println("Python脚本错误输出: " + errorOutput.toString());
                return "发生错误: " + errorOutput.toString();
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "发生错误: " + e.getMessage();
        }
    }

    //智能出题
    @PostMapping("/Question")
    public String generateQuestions(@RequestParam String docUrl) {
        try {
            // 启动 Python 脚本
            ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\YChen\\Desktop\\Study\\Project\\Work\\RedStudyPlatform\\pythonProject\\question.py");
            Process process = processBuilder.start();

            // 获取 Python 脚本的输出流，用来向它传递输入
            OutputStream os = process.getOutputStream();
            PrintWriter writer = new PrintWriter(os);

            // 创建传给 Python 的 JSON 数据
            Map<String, String> data = new HashMap<>();
            data.put("docUrl", docUrl);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInput = objectMapper.writeValueAsString(data);

            // 将 JSON 数据写入 Python 脚本的标准输入
            writer.println(jsonInput);
            writer.flush();
            writer.close();

            // 从 Python 脚本读取输出结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // 读取 Python 脚本的错误输出
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line);
            }

            process.waitFor();

            // 检查是否有错误输出
            if (errorOutput.length() > 0) {
                System.err.println("Python脚本错误输出: " + errorOutput.toString());
                return "发生错误: " + errorOutput.toString();
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "发生错误: " + e.getMessage();
        }
    }

}
