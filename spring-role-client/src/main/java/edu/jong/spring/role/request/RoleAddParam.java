package edu.jong.spring.role.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import edu.jong.spring.common.constants.Patterns;
import edu.jong.spring.role.enums.APIMethod;
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
	@Pattern(regexp = Patterns.ROLE_NAME)
	@Size(max = 30)
	private String name;

	@NotNull
	private APIMethod accessibleMethod;
	
	@NotBlank
	@AntPattern
	@Size(max = 60)
	private String accessibleUrlPattern;

}
