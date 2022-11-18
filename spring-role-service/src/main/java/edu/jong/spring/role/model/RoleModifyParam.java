package edu.jong.spring.role.model;

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
public class RoleModifyParam {

	private APIMethod accessibleMethod;

	@AntPattern
	@Size(max = 60)
	private String accessibleUrlPattern;

}
