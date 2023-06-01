package com.kosa.theBeauty.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kosa.theBeauty.annotation.DebugLog;
import com.kosa.theBeauty.survey.domain.SurveyVO;
import com.kosa.theBeauty.survey.service.SurveyService;
import com.kosa.theBeauty.user.domain.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("survey")
@RequiredArgsConstructor
public class SurveyController {
	
	private final SurveyService service;
	
	// 설문 페이지로 이동
	@DebugLog
	@GetMapping("surveyPage")
	public String surveyPage(@SessionAttribute UserVO currUser, Model model) {
		
		return "survey/surveyPage";
	}
	
	// 설문 가져오기
	@DebugLog
	@PostMapping("surveyPage")
	public String surveyResult(SurveyVO vo) {
		
		service.insertSurvey(vo);
		return "myBeauty"; // 임시페이지
		
	}
}