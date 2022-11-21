package edu.jong.spring.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import edu.jong.spring.common.enums.DataState;
import edu.jong.spring.domain.converter.AbstractAttributeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	private LocalDateTime createDateTime;
	
	@LastModifiedBy
	private LocalDateTime lastUpdateDateTime;

	@Setter
	@Convert(converter = DataStateAttributeConverter.class)
	private DataState dataState = DataState.ACTIVE;

	@Converter
	public static class DataStateAttributeConverter extends AbstractAttributeConverter<DataState, Integer> {
		public DataStateAttributeConverter() {
			super(DataState.class, false);
		}
	}
}
