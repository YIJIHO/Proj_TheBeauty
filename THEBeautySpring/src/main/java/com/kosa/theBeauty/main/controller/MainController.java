package com.kosa.theBeauty.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosa.theBeauty.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("main")
@RequiredArgsConstructor
public class MainController {
   
   // 회원가입 페이지로 이동
   @GetMapping("mainPage")
   public String registerPage() {
      return "main/userMain";
   }
   

}