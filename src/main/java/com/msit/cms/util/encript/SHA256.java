package com.msit.cms.util.encript;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA256 {

	private static Logger logger = LoggerFactory.getLogger(SHA256.class);

	public static String getSalt() {
		java.util.Random random = new java.util.Random();
		byte[] saltBytes = new byte[8];
		random.nextBytes(saltBytes);
		
		StringBuffer salt = new StringBuffer();
		for (int i = 0; i < saltBytes.length; i++)
		{
			salt.append(String.format("%02x",saltBytes[i]));
		}
		return salt.toString();
	}
	
	public static String encrypt(String SrcText,String salt) {
		String returnStr = "";
		MessageDigest msgDigest;
		try 
		{
			msgDigest = MessageDigest.getInstance("SHA-256");
			msgDigest.reset();
			msgDigest.update(salt.getBytes());
			byte[] digest = msgDigest.digest(SrcText.getBytes());
			returnStr = Hex.encodeHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error("[SHA256]에서 익셉션 발생 - " + e.getMessage());
		}
		return returnStr;
	}
	
	public static String encrypt(String SrcText) {
		String returnStr = "";
		MessageDigest msgDigest;
		try 
		{
			msgDigest = MessageDigest.getInstance("SHA-256");
			msgDigest.reset();
			byte[] digest = msgDigest.digest(SrcText.getBytes());
			returnStr = Hex.encodeHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			logger.error("[SHA256]에서 익셉션 발생 - " + e.getMessage());
		}
		return returnStr;
	}

    /**
     * 비밀번호를 암호화하는 기능(복호화가 되면 안되므로 SHA-256 인코딩 방식 적용)
     * 
     * @param password 암호화될 패스워드
     * @param id salt로 사용될 사용자 ID 지정
     * @return
     * @throws Exception
     */
    public static String encryptPassword(String password, String id) throws Exception {

		if (password == null) return "";
		if (id == null) return "";
		
		byte[] hashValue = null; // 해쉬값
	
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		md.reset();
		md.update(id.getBytes());
		
		hashValue = md.digest(password.getBytes());
	
		return new String(Base64.encodeBase64(hashValue));
    }
}
