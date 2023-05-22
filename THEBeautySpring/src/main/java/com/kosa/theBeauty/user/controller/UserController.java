package com.kosa.theBeauty.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosa.theBeauty.annotation.DebugLog;
import com.kosa.theBeauty.user.domain.UserVO;
import com.kosa.theBeauty.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("find")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	// 비밀번호 찾기 페이지로 이동
	@GetMapping("password")
	public String showFindPwPage() {
		
		return "user/FindPw";
	}
	
	// 비밀번호 찾기 실행
	@DebugLog
	@PostMapping("password")
	public String findPwService(@RequestParam String userMail, @RequestParam int userRegistration) {
		
		UserVO userVO = service.findPassword(userMail, userRegistration);
		if(userVO != null) {
			return userVO.getUserPw();
		}
		return "잘못된 요청입니다. 다시 입력 해주세요."; 
	}


}
