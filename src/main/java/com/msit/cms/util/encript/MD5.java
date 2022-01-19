package com.msit.cms.util.encript;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 이강민
 * @desc   현재 MS마트 사용자 및 쇼핑몰 업체 계정의 비밀번호의 경우 MD5로 해시화 되어있음
 * @reason 기존 겟몰의 비밀번호가 MD5로 해시화 되어 복호화가 불가능 하기때문에
 * 		   SHA256+salt는 관리자 및 기타 겟몰에 계정정보가 없었던 정보에만 적용하고 
 *         마이그레이션 데이터들에 대해서는 MD5를 적용함
 */
public class MD5 {
	
	private static Logger logger = LoggerFactory.getLogger(MD5.class);
	
	public static String encode(String str) {
	    String MD5 = "";
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(str.getBytes());
	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        MD5 = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	    	logger.error("[stdWpayPayApproval]에서 익셉션 발생 - " + e.getMessage());
	        MD5 = null;
	    }
	    return MD5;
	}
	
	public static boolean check(String str, String hashed) {
		return encode(str).equals(hashed);
	}
}
