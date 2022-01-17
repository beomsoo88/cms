package com.msit.cms.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.msit.cms.admin.service.AdminDTO;

@Controller
@RequestMapping("admin")
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 관리자 login 페이지
	@RequestMapping("/login")
	public ModelAndView adminLoginView(HttpServletRequest req) {
		logger.debug(req.getRequestURL().toString()+" 페이지 요청");
		ModelAndView mv = new ModelAndView("admin/login");
		return mv;
	}

	// 관리자 login action
	@RequestMapping("/login-action")
	public ModelAndView adminLoginAction(AdminDTO adminDTO, HttpServletRequest req) {
		
		// 공통 처리
		ModelAndView mv = new ModelAndView("common/message");
		mv.addObject("returnType",":submit");
		mv.addObject("returnUrl","/admin/login");
		mv.addObject("hiddenName1", adminDTO.getAdminId());
		mv.addObject("hiddenValue1", adminDTO.getAdminPw());
		return mv;
	}
}
