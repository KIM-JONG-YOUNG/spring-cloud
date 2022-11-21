package edu.jong.spring.member.enums;

import edu.jong.spring.common.enums.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender implements CodeEnum<String> {

	MALE("M"),
	FEMALE("F");
	
	private final String code;
	
}
