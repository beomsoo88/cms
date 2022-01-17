package com.msit.cms.common.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.msit.cms.common.service.CmsStaticTbDTO;

@Mapper
public interface CmsStaticTbMapper {

	List<CmsStaticTbDTO> selectCmsStaticTbList(CmsStaticTbDTO eDTO) throws Exception;

	int selectCmsStaticTbCount(CmsStaticTbDTO eDTO) throws Exception;

	int insertCmsStaticTb(CmsStaticTbDTO eDTO) throws Exception;

	int updateCmsStaticTb(CmsStaticTbDTO eDTO) throws Exception;

	int deleteCmsStaticTb(String keyId) throws Exception;

}
