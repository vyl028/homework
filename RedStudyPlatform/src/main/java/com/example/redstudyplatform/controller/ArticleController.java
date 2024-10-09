package com.example.redstudyplatform.controller;
//测试登录功能用


import com.example.redstudyplatform.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("所有文章数据");
    }
}
