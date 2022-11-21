package edu.jong.spring.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataState implements CodeEnum<Integer> {

	ACTIVE(1), DEACTIVE(0);

	private final Integer code;

}
