package com.msit.cms.model.generator;

import java.util.List;
import java.util.Map;

public interface GeneratorService {

	public List<Map<String, Object>> showTables(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> useTable(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> showSchemas(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> showTableDefinition(Map<String, Object> map) throws Exception;
	
}