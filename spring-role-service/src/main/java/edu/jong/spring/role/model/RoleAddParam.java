package edu.jong.spring.role.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import edu.jong.spring.role.validate.AntPattern;
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
	@Size(max = 30)
	@Pattern(regexp = "^ROLE_[A-Z]+")
	private String name;

	@NotNull
	private APIMethod accessibleMethod;
	
	@NotBlank
	@Size(max = 60)
	@AntPattern
	private String accessibleUrlPattern;

}
