package edu.jong.spring.role.model;

import javax.persistence.Converter;

import edu.jong.spring.domain.model.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIMethod implements CodeEnum<String> {

	ALL("ALL"),
	GET("GET"),
	PUT("PUT"),
	DELETE("DELETE"),
	POST("POST");
	
	private final String code;
	
	@Converter
	public static class AttributeConverter extends AbstractAttributeConverter<APIMethod, String> {
		public AttributeConverter() {
			super(APIMethod.class, false);
		}
	}
	
}
