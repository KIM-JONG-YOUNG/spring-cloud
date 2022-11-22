package edu.jong.spring.login.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import edu.jong.spring.role.enums.APIMethod;
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
public class AccessCheckParam {

	@NotNull
	private APIMethod checkMethod;
	
	@NotBlank
	private String checkURL;

}
