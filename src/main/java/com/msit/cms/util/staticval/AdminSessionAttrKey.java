package com.msit.cms.util.staticval;

/**
 * @author LKM
 * 세션 발급시 세션 어트리뷰트에 사용할 키 리스트(인터셉터와 공유 예정)
 */
public class AdminSessionAttrKey {
	
	public final static String LOGIN_TYPE = "loginType";    // 관리자 adm / 업체 st
	public final static String IS_BOTH    = "isBoth";    // 관리자 adm / 업체 st
	public final static String LOGIN_NAME = "loginName";    // 
	public final static String ID         = "admId"    ;    // 
	public final static String AUTH       = "authCd"   ;    // 
	public final static String IDX        = "admCd"    ;    // 
	public final static String NAME       = "admNm"    ;    // 
	
	public final static String[] mgmtSessions = {ID, AUTH, IDX, NAME, LOGIN_TYPE};
}
