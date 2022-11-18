package edu.jong.spring.role.model;

import java.time.LocalDateTime;

import edu.jong.spring.domain.model.DataState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleDetails {

	private long no;
	private String name;
	private APIMethod accessibleMethod;
	private String accessibleUrlPattern;

	private LocalDateTime createDateTime;
	private LocalDateTime lastUpdateDateTime;
	private DataState dataState;
	
}
