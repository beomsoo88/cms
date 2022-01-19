package com.msit.cms.util.encript;

public class KeyStored {
	
	//서버 기동시 공통으로 사용할 AES256 키
	private static String aek256Key = "";

	public static String getAek256Key() {
		return aek256Key;
	}

	public static void setAek256Key(String aek256Key) {
		KeyStored.aek256Key = aek256Key;
	}
	
}
