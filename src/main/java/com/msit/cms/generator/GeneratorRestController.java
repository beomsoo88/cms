package com.msit.cms.generator;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/generator")
public class GeneratorRestController {
	
	@Autowired GeneratorService service;
	@Autowired ResourceLoader resourceLoader;
	
	// template File classpath..
	private final static String CONTROLLER_TEMPLATE    = "classpath:codesample/Controller.txt";
	private final static String DTO_TEMPLATE           = "classpath:codesample/DTO.txt";
	private final static String MAPPER_TEMPLATE        = "classpath:codesample/Mapper_dto.txt";
	private final static String DAO_TEMPLATE           = "classpath:codesample/DAO.txt";
	private final static String SERVICE_TEMPLATE       = "classpath:codesample/Service_Interface.txt";
	private final static String SERVICE_IMPLE_TEMPLATE = "classpath:codesample/Service_Implements.txt";
	
	@RequestMapping(value = "/showTables", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showTables(@RequestParam Map<String, Object> input) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> returns = new HashMap<String, Object>();
		map.put("schemaName", input.get("schemaName").toString());
		//service.useTable(map);
		Map<String, Object> mapSchema = new HashMap<String, Object>();
		mapSchema.put("schemaName", input.get("schemaName").toString());
		List<Map<String, Object>> result = service.showTables(mapSchema);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		for(Map<String, Object> row : result) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("tablename", row.get("Tables_in_" + input.get("schemaName").toString()).toString());
			resultList.add(res);
		}
		returns.put("list", resultList);
		return returns;
	}

	@RequestMapping(value = "/showSchemas", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showtables() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> returns = new HashMap<String, Object>();

		List<Map<String, Object>> result = service.showSchemas(map);
		returns.put("list", result);
		return returns;
	}

	@RequestMapping(value = "/Service", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> service(@RequestParam Map<String, Object> input) throws Exception {
		Map<String, Object> returns = new HashMap<String, Object>();
		
		Resource resource = resourceLoader.getResource(SERVICE_TEMPLATE);
		InputStream inputStream = resource.getInputStream();
		String textIfce = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		
		Resource resourceImpl = resourceLoader.getResource(SERVICE_IMPLE_TEMPLATE);
		InputStream inputStream2 = resourceImpl.getInputStream();
		String textImpl = IOUtils.toString(inputStream2, StandardCharsets.UTF_8.name());
		
		GeneratorUtil.generateService(input, textIfce, textImpl);
		
		returns.put("result", true);
		return returns;
	}
	
	@RequestMapping(value = "/Dao", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Dao(@RequestParam Map<String, Object> input) throws Exception {
		Map<String, Object> returns = new HashMap<String, Object>();

		Resource resource = resourceLoader.getResource(DAO_TEMPLATE);
		InputStream inputStream = resource.getInputStream();
		String textIfce = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		GeneratorUtil.generateDao(input, textIfce);

		returns.put("result", true);
		return returns;
	}
	
	@RequestMapping(value = "/Dto", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dto(@RequestParam Map<String, Object> input) throws Exception {
		Map<String, Object> returns = new HashMap<String, Object>();
		Resource resource = resourceLoader.getResource(DTO_TEMPLATE);
		InputStream inputStream = resource.getInputStream();
		String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		
		List<Map<String, Object>> queryResult = service.showTableDefinition(input);
		
		GeneratorUtil.generateDto(input, text, queryResult);
		
		returns.put("result", true);
		return returns;
	}

	@RequestMapping(value = "/Mapper", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> mapper(@RequestParam Map<String, Object> input) throws Exception {
		Map<String, Object> returns = new HashMap<String, Object>();
		
		Resource resource = resourceLoader.getResource(MAPPER_TEMPLATE);
		InputStream inputStream = resource.getInputStream();
		String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		List<Map<String, Object>> queryResult = service.showTableDefinition(input);

		GeneratorUtil.generateMapper(input, text, queryResult);
		
		returns.put("result", true);
		return returns;
	}
	
	@RequestMapping(value = "/MapperWithDTO", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> mapperWithDTO(@RequestParam Map<String, Object> input) throws Exception {
		Map<String, Object> returns = new HashMap<String, Object>();
		
		Resource resource = resourceLoader.getResource(MAPPER_TEMPLATE);
		InputStream inputStream = resource.getInputStream();
		String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		List<Map<String, Object>> queryResult = service.showTableDefinition(input);

		GeneratorUtil.generateMapper(input, text, queryResult);
		
		returns.put("result", true);
		return returns;
	}
	
	@RequestMapping(value = "/All", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> makeAll(@RequestBody Map<String, Object> inputArray) throws Exception {
		Map<String, Object> returns = new HashMap<String, Object>();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> tables = (List<Map<String, Object>>) inputArray.get("tables");
		
		Resource resource = resourceLoader.getResource(MAPPER_TEMPLATE);
		InputStream inputStreamMapper = resource.getInputStream();
		String mapperTemplate = IOUtils.toString(inputStreamMapper, StandardCharsets.UTF_8.name());
		
		resource = resourceLoader.getResource(DTO_TEMPLATE);
		InputStream inputStreamDTO = resource.getInputStream();
		String DTOTemplate = IOUtils.toString(inputStreamDTO, StandardCharsets.UTF_8.name());
		
		resource = resourceLoader.getResource(SERVICE_TEMPLATE);
		InputStream inputStreamServiceIfc = resource.getInputStream();
		String ServiceIfcTemplate = IOUtils.toString(inputStreamServiceIfc, StandardCharsets.UTF_8.name());
		
		Resource resourceImpl = resourceLoader.getResource(SERVICE_IMPLE_TEMPLATE);
		InputStream inputStreamServiceImpl = resourceImpl.getInputStream();
		String ServiceImplTemplate = IOUtils.toString(inputStreamServiceImpl, StandardCharsets.UTF_8.name());
		
		resource = resourceLoader.getResource(DAO_TEMPLATE);
		InputStream inputStreamDaoIfc = resource.getInputStream();
		String DaoIfce = IOUtils.toString(inputStreamDaoIfc, StandardCharsets.UTF_8.name());
		
		for(Map<String, Object> input : tables) {
			List<Map<String, Object>> queryResult = service.showTableDefinition(input);
			//Mapper 생성부
			GeneratorUtil.generateMapper(input, mapperTemplate, queryResult);
			//DTO
			GeneratorUtil.generateDto(input, DTOTemplate, queryResult);
			//Service
			GeneratorUtil.generateService(input, ServiceIfcTemplate, ServiceImplTemplate);
			//Dao
			GeneratorUtil.generateDao(input, DaoIfce);
		}
		returns.put("result", true);
		return returns;
	}
	
	@RequestMapping(value = "/gen", method = RequestMethod.GET)
	public String home(@RequestParam Map<String, Object> requestParam) throws Exception {
		return "generator";
	}
}
