package com.msit.cms.generator;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper	
public interface GeneratorMapper {
	
	public List<Map<String, Object>> showTables(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> useTable(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> showSchemas(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> showTableDefinition(Map<String, Object> map) throws Exception;
	
}