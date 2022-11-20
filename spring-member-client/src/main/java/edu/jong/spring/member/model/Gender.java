package edu.jong.spring.member.model;

import javax.persistence.Converter;

import edu.jong.spring.domain.model.CodeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender implements CodeEnum<String> {

	MALE("M"),
	FEMALE("F");
	
	private final String code;
	
	@Converter
	public static class AttributeConverter extends AbstractAttributeConverter<Gender, String> {
		public AttributeConverter() {
			super(Gender.class, false);
		}
	}
	
}
