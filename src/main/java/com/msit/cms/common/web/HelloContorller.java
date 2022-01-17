package com.msit.cms.common.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloContorller {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//admin/login
	@RequestMapping(value = "hello")
	public ModelAndView loginView(HttpServletRequest req, Model model) throws Exception {
		// 로그인 페이지
		logger.debug(req.getRequestURL().toString()+" 페이지 요청");
		ModelAndView mv = new ModelAndView("hello");
		mv.addObject("name","김범수");
		return mv;
	}

	//admin/login
	@RequestMapping(value = "hello-thymeleaf")
	public ModelAndView loginThymeleaf(HttpServletRequest req, Model model) throws Exception {
		// 로그인 페이지
		logger.debug(req.getRequestURL().toString()+" 페이지 요청");
		ModelAndView mv = new ModelAndView("thymeleaf/header");
		mv.addObject("name","김범수");
		return mv;
	}
}
