package edu.jong.spring.domain.model;

import java.util.EnumSet;
import java.util.Optional;

import javax.persistence.AttributeConverter;

import lombok.RequiredArgsConstructor;

public interface CodeEnum<C> {

	C getCode();

	@RequiredArgsConstructor
	public static abstract class AbstractAttributeConverter<E extends Enum<E> & CodeEnum<V>, V> implements AttributeConverter<E, V>{

		private final Class<E> enumType;
		private final boolean nullable;
		
		@Override
		public V convertToDatabaseColumn(E attribute) {

			if (!this.nullable && attribute == null) 
				throw new IllegalArgumentException(
						String.format("%s를 DB에 Null로 저장할 수 없습니다.", this.enumType.getSimpleName()));
			
			return (attribute == null) ? null : attribute.getCode();
		}

		@Override
		public E convertToEntityAttribute(V dbData) {

			if (!this.nullable && dbData == null) 
				throw new IllegalArgumentException(
						String.format("%s가 DB에 Null로 저장되어 있습니다.", this.enumType.getSimpleName()));
			
			Optional<E> enumProp = EnumSet.allOf(enumType).stream()
					.filter(x -> x.getCode().equals(dbData)).findAny();
			
			return enumProp.orElseThrow(() -> new IllegalArgumentException(
							String.format("%s는 %s에 존재하지 않는 값입니다.", dbData, this.enumType.getSimpleName())));
		}

	}
}