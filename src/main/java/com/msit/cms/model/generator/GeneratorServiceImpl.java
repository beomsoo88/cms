package com.msit.cms.model.generator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratorServiceImpl implements GeneratorService {

	@Autowired GeneratorMapper mapper;

	public List<Map<String, Object>> showTables(Map<String, Object> map) throws Exception {
		return mapper.showTables(map);
	}
	
	public List<Map<String, Object>> useTable(Map<String, Object> map) throws Exception {
		return mapper.useTable(map);
	}
	
	public List<Map<String, Object>> showSchemas(Map<String, Object> map) throws Exception {
		return mapper.showSchemas(map);
	}

	@Override
	public List<Map<String, Object>> showTableDefinition(Map<String, Object> map) throws Exception {
		return mapper.showTableDefinition(map);
	}
}



