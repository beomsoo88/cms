package com.msit.cms.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msit.cms.common.service.CmsStaticTbDTO;
import com.msit.cms.common.service.CmsStaticTbService;

@Service
public class CmsStaticTbServiceImpl implements CmsStaticTbService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired CmsStaticTbMapper cmsStaticTbMapper;
	
	// SELECT (단건 사용시에도 공통 사용가능)
	public List<CmsStaticTbDTO> getCmsStaticTbList(CmsStaticTbDTO eDTO) throws Exception {
		return cmsStaticTbMapper.selectCmsStaticTbList(eDTO);
	};

	// SELECT 조건에 따른 Count
	public int getCmsStaticTbCount(CmsStaticTbDTO eDTO) throws Exception {
		return (Integer)cmsStaticTbMapper.selectCmsStaticTbCount(eDTO);
	};

	// INSERT
	public int addCmsStaticTb(CmsStaticTbDTO eDTO) throws Exception {
		return cmsStaticTbMapper.insertCmsStaticTb(eDTO);
	};

	// UPDATE PK값에 따른 업데이트
	public int chgCmsStaticTb(CmsStaticTbDTO eDTO) throws Exception {
		return cmsStaticTbMapper.updateCmsStaticTb(eDTO);
	};

	// DELETE 삭제 로직(Del_yn = 'Y' 업데이트 로직으로 사용)
	// 해당로직은 물리적으로 데이터 삭제시에만 사용
	public int delCmsStaticTb(String keyId) throws Exception {
		return cmsStaticTbMapper.deleteCmsStaticTb(keyId);
	};

}
