package edu.jong.spring.role.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class RoleAddParam {

	@NotBlank
	private String name;

	@NotNull
	private APIMethod accessibleMethod;
	
	@NotBlank
	private String accessibleUrlPattern;

}
