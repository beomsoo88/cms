package com.msit.cms.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.msit.cms.util.staticval.AdminSessionAttrKey;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter{
	
	// SLF4J LOGGER
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	String[] checkSessionAttributes = AdminSessionAttrKey.mgmtSessions;
	String redirectUrl = "/login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		logger.debug("\n\n[인터셉터 정보]=============================================================="
				+ "\n * " +request.getRequestURL().toString()
				+ "\n========================================================================\n");
		//AJAX 요청인지 먼저 확인
		if(isAjaxRequest(request)) {
			//세션이 없으면 Login page로 이동 ,, 있으면 권한체크 후 다음로직 
			for(String sa : checkSessionAttributes) {
				if (session.getAttribute(sa) == null) {
					response.setStatus(401);
					return false;
				}
			}
		} else {
			for(String sa : checkSessionAttributes) {
				if (session.getAttribute(sa) == null) {
					request.getSession().setAttribute("lastRequestUrl", request.getRequestURL());
					response.setStatus(401);
					response.sendRedirect(redirectUrl);
					return false;
				}
			}
		}
		return true;
	}

	private boolean isAjaxRequest(HttpServletRequest req) {
		String isAjax = req.getHeader("ajax");
		if(isAjax == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
