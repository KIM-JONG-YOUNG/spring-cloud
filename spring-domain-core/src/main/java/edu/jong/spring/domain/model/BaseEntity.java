package edu.jong.spring.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
	@Convert(converter = DataState.AttributeConverter.class)
	private DataState dataState = DataState.ACTIVE;

}
