package com.msit.cms.generator;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GeneratorViewController {

	@RequestMapping(value = "/generator", method = RequestMethod.GET)
	public ModelAndView generator(@RequestParam Map<String, Object> requestParam) throws Exception {
		ModelAndView mv = new ModelAndView("sample/generator");
		return mv;
	}
}
