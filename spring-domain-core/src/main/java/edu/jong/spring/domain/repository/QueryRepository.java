package edu.jong.spring.domain.repository;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.StringPath;

public interface QueryRepository {
	
	default <E extends Enum<E>> BooleanExpression equalsIfPresent(EnumPath<E> path, E value) {
		return (value == null) ? null : path.eq(value);
	}

	default BooleanExpression containsIfPresent(StringPath path, String value) {
		return (StringUtils.isBlank(value)) ? null : path.contains(value);
	}

	default BooleanExpression betweenIfPresent(DateTimePath<LocalDateTime> path, LocalDateTime from, LocalDateTime to) {

		if (from != null && to != null) {
			return path.between(from, to);
		} else if (from != null) {
			return path.after(from);
		} else if (to != null) {
			return path.before(to);
		} else {
			return null;
		}
	}
	
}
