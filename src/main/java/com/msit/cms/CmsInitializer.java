package com.msit.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.msit.cms.common.service.CmsStaticTbDTO;
import com.msit.cms.common.service.CmsStaticTbService;
import com.msit.cms.util.staticval.DBStaticValue;

@Component
public class CmsInitializer implements ApplicationRunner{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired CmsStaticTbService staticService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<CmsStaticTbDTO> list = staticService.getCmsStaticTbList(new CmsStaticTbDTO());
		
		log.error("[프로젝트 기동] " + list.size() +"개 상수 설정 시작...");
		
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> innerMap = new HashMap<String, Object>();
			innerMap.put("CODE", list.get(i).getCode());
			innerMap.put("VAL", list.get(i).getStaticVal1());
			innerMap.put("VAL_2", list.get(i).getStaticVal2());
			innerMap.put("VAL_3", list.get(i).getStaticVal3());
			map.put(list.get(i).getCode(), innerMap);
		}
		DBStaticValue.codeMap = map;
		
		log.error("[프로젝트 기동] 상수 설정 완료");
		
	}
}
