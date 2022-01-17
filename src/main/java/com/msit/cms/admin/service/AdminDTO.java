package com.msit.cms.admin.service;

import com.msit.cms.common.service.CommonDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO extends CommonDTO {

	private String adminIdx;		// 관리자 IDX
	private String adminId;			// 관리자 아이디
	private String adminPw;			// 비밀번호
	private String adminName;		// 이름
	private String adminRole;		// 권한
	private String regId;			// 등록자
	private String regDate;			// 등록일
	private String updId;			// 최종수정자
	private String updDate;			// 최종수정일
	private String useFlag;			// 상태 (승인: Y / 삭제: N / 잠금: L / 대기: S / 만료: E) 2020.09.02 대기, 만료 상태 추가
	private String lastIp;			// 최종 로그인 IP
	private String tryIp;			// 최근 로그인 시도 IP
}
