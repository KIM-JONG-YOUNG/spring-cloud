package edu.jong.spring.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DataState implements CodeEnum<Integer>{

	ACTIVE(1), DEACTIVE(0);
	
	private final Integer code;
	
	public static class AttributeConverter extends AbstractAttributeConverter<DataState, Integer> {
		public AttributeConverter() {
			super(DataState.class, false);
		}
	}
	
}
