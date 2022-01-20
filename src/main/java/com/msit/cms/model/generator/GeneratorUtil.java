package com.msit.cms.model.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msit.cms.common.service.CommonDTO;

public class GeneratorUtil {
	
	public static String JAVA_PATH_SUFFIX = "\\src\\main\\java";
	
	public static String RESOURCES_PATH_SUFFIX = "\\src\\main\\resources";
	
	public static String getPackageStr() {
		String pkg = GeneratorRestController.class.getPackage().getName();
		String[] pkgSplit = pkg.split("\\.");
		return pkgSplit[0] + "." + pkgSplit[1] + "." + pkgSplit[2] + "." + pkgSplit[3];
	}
	
	public static String getAddPathStr() {
		String pkg = GeneratorRestController.class.getPackage().getName();
		String[] pkgSplit = pkg.split("\\.");
		return pkgSplit[0] + "\\" + pkgSplit[1] + "\\" + pkgSplit[2] + "\\" + pkgSplit[3] + "\\";
	}
	
	public static String convertCamelCase(String s) {
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '_') {
				sb.deleteCharAt(i);
				sb.replace(i, i+1, String.valueOf(Character.toUpperCase(sb.charAt(i))));
			}
		}
		return sb.toString();
	}
	
	public static String convertPascalCase(String s) {
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s);
		sb.replace(0, 1, String.valueOf(Character.toUpperCase(sb.charAt(0))));
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '_') {
				sb.deleteCharAt(i);
				sb.replace(i, i+1, String.valueOf(Character.toUpperCase(sb.charAt(i))));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 테이블 명에 따른 패키지 생성명 조회
	 * ex) cms_admin_tb 라면 admin 만 가져오도록 cms_admin_log_tb 라도 admin만 return
	 * @param s
	 * @return
	 */
	public static String getPackageName(String s) {
		s = s.toLowerCase();
		return s.split("_")[1];
	}
	
	public static void generateDao(Map<String, Object> input, String textIfce) throws Exception {
		
		String TableName = GeneratorUtil.convertPascalCase(input.get("tableName").toString().toUpperCase());
		String tableName = GeneratorUtil.convertCamelCase(input.get("tableName").toString().toUpperCase());
		
		String realPackageName = GeneratorUtil.getPackageName(input.get("tableName").toString().toUpperCase());
		String DAOPackeage = "service.impl";
		String PackageName = getPackageStr().replaceAll("model", realPackageName);
		textIfce = textIfce.replaceAll("%\\(PackageName\\)%", PackageName);
		textIfce = textIfce.replaceAll("%\\(TableName\\)%", TableName);
		textIfce = textIfce.replaceAll("%\\(tableName\\)%", tableName);
		textIfce = textIfce.replaceAll("%\\(PackageAdd\\)%", DAOPackeage);

		// fileDir 조회 및 생성
		String addPath = getAddPathStr().replace("model", realPackageName);
		String filePath = input.get("folderPath").toString() + JAVA_PATH_SUFFIX + "\\" + addPath + DAOPackeage.replace(".","\\");
		File fileDir =  new File(filePath);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		// file 생성
		filePath = filePath + "\\" + TableName + "Mapper.java";
		OutputStream output = new FileOutputStream(filePath);
		byte[] by=textIfce.getBytes();
		output.write(by);
		output.close();
	}
	public static void generateDaoImpl(Map<String, Object> input, String textImpl) throws Exception {
		String PackageName = getPackageStr();
		
		String TableName = GeneratorUtil.convertPascalCase(input.get("tableName").toString().toUpperCase());
		String tableName = GeneratorUtil.convertCamelCase(input.get("tableName").toString().toUpperCase());
		
		String DAOPackeage = "dao";
		
		textImpl = textImpl.replaceAll("%\\(PackageName\\)%", PackageName);
		textImpl = textImpl.replaceAll("%\\(TableName\\)%", TableName);
		textImpl = textImpl.replaceAll("%\\(tableName\\)%", tableName);
		textImpl = textImpl.replaceAll("%\\(PackageAdd\\)%", DAOPackeage);
		
		String addPath = getAddPathStr();
		
		String filePathImpl = input.get("folderPath").toString() + JAVA_PATH_SUFFIX + "\\" + addPath + DAOPackeage + "\\" + TableName + "DAOImpl.java";
		OutputStream output2 = new FileOutputStream(filePathImpl);
		byte[] by2=textImpl.getBytes();
		output2.write(by2);
		output2.close();
	}
	
	public static void generateService(Map<String, Object> input, String textIfce, String textImpl) throws Exception{
		
		String TableName = GeneratorUtil.convertPascalCase(input.get("tableName").toString().toUpperCase());
		String tableName = GeneratorUtil.convertCamelCase(input.get("tableName").toString().toUpperCase());

		String realPackageName = GeneratorUtil.getPackageName(input.get("tableName").toString().toUpperCase());
		String ServicePackeage = "service";
		String PackageName = getPackageStr().replaceAll("model", realPackageName);
		textIfce = textIfce.replaceAll("%\\(PackageName\\)%", PackageName);
		textIfce = textIfce.replaceAll("%\\(TableName\\)%", TableName);
		textIfce = textIfce.replaceAll("%\\(tableName\\)%", tableName);
		textIfce = textIfce.replaceAll("%\\(PackageAdd\\)%", ServicePackeage);

		// fileDir 조회 및 생성
		String addPath = getAddPathStr().replace("model", realPackageName);
		String filePathImpl = input.get("folderPath").toString() + JAVA_PATH_SUFFIX + "\\" + addPath + ServicePackeage;
		File fileDir =  new File(filePathImpl);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		// file 생성
		filePathImpl = filePathImpl + "\\" + TableName + "Service.java";
		OutputStream output2 = new FileOutputStream(filePathImpl);
		byte[] by2=textIfce.getBytes();
		output2.write(by2);
		output2.close();
	}
	
	public static void generateServiceImpl(Map<String, Object> input, String textIfce, String textImpl) throws Exception{
		
		String TableName = GeneratorUtil.convertPascalCase(input.get("tableName").toString().toUpperCase());
		String tableName = GeneratorUtil.convertCamelCase(input.get("tableName").toString().toUpperCase());

		String realPackageName = GeneratorUtil.getPackageName(input.get("tableName").toString().toUpperCase());
		String ServicePackeage = "service.impl";
		String PackageName = getPackageStr().replaceAll("model", realPackageName);
		textImpl = textImpl.replaceAll("%\\(PackageName\\)%", PackageName);
		textImpl = textImpl.replaceAll("%\\(TableName\\)%", TableName);
		textImpl = textImpl.replaceAll("%\\(tableName\\)%", tableName);
		textImpl = textImpl.replaceAll("%\\(PackageAdd\\)%", ServicePackeage);

		// fileDir 조회 및 생성
		String addPath = getAddPathStr().replace("model", realPackageName);
		String filePathImpl = input.get("folderPath").toString() + JAVA_PATH_SUFFIX + "\\" + addPath + ServicePackeage.replace(".","\\");
		File fileDir =  new File(filePathImpl);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		// file 생성
		filePathImpl = filePathImpl + "\\" + TableName + "ServiceImpl.java";
		OutputStream output2 = new FileOutputStream(filePathImpl);
		byte[] by2=textImpl.getBytes();
		output2.write(by2);
		output2.close();
	}
	
	public static void generateMapper(Map<String, Object> input, String text, List<Map<String, Object>> queryResult ) throws Exception {

		String realPackageName = GeneratorUtil.getPackageName(input.get("tableName").toString().toUpperCase());
		String PackageName = getPackageStr().replace("model", realPackageName) + ".service";
		String DaoPackageName = getPackageStr().replace("model", realPackageName) + ".service.impl";

		String KEY_NAME = queryResult.get(0).get("field").toString().toUpperCase();
		String TABLE_NAME = input.get("tableName").toString().toUpperCase();
		String TableName = GeneratorUtil.convertPascalCase(TABLE_NAME);
		String xmlFileName = GeneratorUtil.convertCamelCase(TABLE_NAME);
		String keyName = GeneratorUtil.convertCamelCase(KEY_NAME);

		String MYBATIS_WHERE_COLUMNS = "";
		String MYBATIS_INSERT_COLUMNS_COLUMN = "";
		String MYBATIS_INSERT_COLUMNS_VALUE = "";
		String MYBATIS_UPDATE_COLUMNS = "";
		String COLUMN_LIST = "";
		
		String prefix = "\n\t\t\t";
		
		int i=0;
		for(Map<String, Object> row : queryResult) {
			String camel = String.format("%-25s", GeneratorUtil.convertCamelCase(row.get("field").toString()));
			String tempCamel = GeneratorUtil.convertCamelCase(row.get("field").toString());
			
			String underscore = row.get("field").toString().toLowerCase();
			if(i == 0) {
				COLUMN_LIST += prefix + underscore; 
			} else {
				COLUMN_LIST += prefix + ", " + underscore; 
			}
			if(camel.trim().equals("regDt")) {
				MYBATIS_INSERT_COLUMNS_COLUMN += prefix + " , reg_dt";
				MYBATIS_INSERT_COLUMNS_VALUE  += prefix + " , now()";
			}else {
				MYBATIS_INSERT_COLUMNS_COLUMN += prefix + "<if test=\"" + camel + " !=null and "+camel+" !=''\"> , "+underscore+" </if>";
				MYBATIS_INSERT_COLUMNS_VALUE  += prefix + "<if test=\"" + camel + " !=null and "+camel+" !=''\"> , #{"+tempCamel+"} </if>";
			}
			if(!camel.trim().equals(keyName)) {
				if(camel.trim().equals("modDt")) {
					MYBATIS_UPDATE_COLUMNS        += prefix + " , mod_dt = now()";
				} else {
					MYBATIS_UPDATE_COLUMNS        += prefix + "<if test=\"" + camel + " !=null and "+camel+" !=''\"> , "+underscore+" = #{"+tempCamel+"} </if>";
				}
			}
			if(row.get("type").toString().toUpperCase().indexOf("datetime") > -1) {
				String searchFrom = String.format("%-25s", tempCamel + "SearchFrom");
				MYBATIS_WHERE_COLUMNS += prefix + "<if test=\"" + searchFrom + " !=null and " + searchFrom + " !=''\"><![CDATA[ and date("+underscore+") >= date(#{"+tempCamel+"SearchFrom}) ]]></if>";
				String searchTo = String.format("%-25s", tempCamel + "SearchTo");
				MYBATIS_WHERE_COLUMNS += prefix + "<if test=\"" + searchTo + " !=null and " + searchTo + " !=''\"><![CDATA[ and date("+underscore+") <= date(#{"+tempCamel+"SearchTo}) ]]></if>";
			} else {
				MYBATIS_WHERE_COLUMNS += prefix + "<if test=\"" + camel + " !=null and " + camel + " !=''\"><![CDATA[ and " + underscore + " = #{" + tempCamel + "} ]]></if>";
			}
			i++;
		}

		text = text.replaceAll("%\\(PackageName\\)%", PackageName);
		text = text.replaceAll("%\\(DaoPackageName\\)%", DaoPackageName);
		text = text.replaceAll("%\\(KEY_NAME\\)%", KEY_NAME.toLowerCase());
		text = text.replaceAll("%\\(TABLE_NAME\\)%", TABLE_NAME.toLowerCase());
		text = text.replaceAll("%\\(TableName\\)%", TableName);
		text = text.replaceAll("%\\(tableName\\)%", xmlFileName);
		text = text.replaceAll("%\\(xmlFileName\\)%", xmlFileName);
		text = text.replaceAll("%\\(keyName\\)%", keyName);
		text = text.replaceAll("%\\(MYBATIS_WHERE_COLUMNS\\)%", MYBATIS_WHERE_COLUMNS);
		text = text.replaceAll("%\\(MYBATIS_INSERT_COLUMNS_COLUMN\\)%", MYBATIS_INSERT_COLUMNS_COLUMN);
		text = text.replaceAll("%\\(MYBATIS_INSERT_COLUMNS_VALUE\\)%", MYBATIS_INSERT_COLUMNS_VALUE);
		text = text.replaceAll("%\\(MYBATIS_UPDATE_COLUMNS\\)%", MYBATIS_UPDATE_COLUMNS);
		text = text.replaceAll("%\\(COLUMN_LIST\\)%", COLUMN_LIST);
		
		String filePath = input.get("folderPath").toString() + RESOURCES_PATH_SUFFIX + "\\mappers\\" + TableName + "Mapper.xml";
		OutputStream output = new FileOutputStream(filePath);
		byte[] by=text.getBytes();
		output.write(by);
		output.close();
		
	}
	
	public static void generateDto(Map<String, Object> input, String text, List<Map<String, Object>> queryResult ) throws Exception {
		
		String TableName = GeneratorUtil.convertPascalCase(input.get("tableName").toString().toUpperCase());
		String columnVariablesList = "";
		String prefix = "\n\t";
		
		Field[] fields = CommonDTO.class.getDeclaredFields();    
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		for(Field fld : fields) {
			fieldMap.put(fld.getName().toString(), "1");
		}
		
		for(Map<String, Object> row : queryResult) {
			String camel = GeneratorUtil.convertCamelCase(row.get("field").toString());
			
			if(!fieldMap.containsKey(camel)) {
				if(row.get("type").toString().toUpperCase().indexOf("datetime") > -1) {
					columnVariablesList += prefix + String.format("%-40s", "private String " + camel) + "= null;";
					columnVariablesList += prefix + String.format("%-40s", "private String " + camel+"SearchFrom") + "= null;";
					columnVariablesList += prefix + String.format("%-40s", "private String " + camel+"SearchTo") + "= null;";
				} else {
					columnVariablesList += prefix + String.format("%-40s", "private String " + camel) + "= null;";
				}
			}
		}

		String realPackageName = GeneratorUtil.getPackageName(input.get("tableName").toString().toUpperCase());
		String DTOPackeage = "service";
		String PackageName = getPackageStr().replaceAll("model", realPackageName);
		String commonPackage = PackageName.replaceAll(realPackageName, "common");
		text = text.replaceAll("%\\(CommonPackageName\\)%", commonPackage);
		text = text.replaceAll("%\\(PackageName\\)%", PackageName);
		text = text.replaceAll("%\\(TableName\\)%", TableName);
		text = text.replaceAll("%\\(columnVariablesList\\)%", columnVariablesList);
		text = text.replaceAll("%\\(PackageAdd\\)%", DTOPackeage);
		
		// fileDir 조회 및 생성
		String addPath = getAddPathStr().replace("model", realPackageName);
		String filePath = input.get("folderPath").toString() + JAVA_PATH_SUFFIX + "\\" + addPath + DTOPackeage;
		File fileDir =  new File(filePath);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		// file 생성
		filePath = filePath + "\\" + TableName + "DTO.java";
		OutputStream output = new FileOutputStream(filePath);
		byte[] by=text.getBytes();
		output.write(by);
		output.close();
	}
}
