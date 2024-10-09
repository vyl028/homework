package com.example.redstudyplatform.controller;

import com.example.redstudyplatform.domain.ExplicitRating;
import com.example.redstudyplatform.domain.ImplicitRating;
import com.example.redstudyplatform.service.ExplicitRatingService;
import com.example.redstudyplatform.service.ImplicitRatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ExplicitRatingService explicitRatingService;

    @Autowired
    private ImplicitRatingService implicitRatingService;

    @PostMapping("/runPython")
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
}
