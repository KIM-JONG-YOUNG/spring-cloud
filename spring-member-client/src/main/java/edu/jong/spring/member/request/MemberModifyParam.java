package edu.jong.spring.member.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import edu.jong.spring.member.enums.Gender;
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
public class MemberModifyParam {

	@Size(max = 30)
	private String name;
	
	private Gender gender;
	
	@Email
	@Size(max = 60)
	private String email;

}
