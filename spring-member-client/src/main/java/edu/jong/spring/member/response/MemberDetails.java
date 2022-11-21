package edu.jong.spring.member.response;

import java.time.LocalDateTime;

import edu.jong.spring.common.enums.DataState;
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
public class MemberDetails {

	private long no;
	private String username;
	private String name;
	private Gender gender;
	private String email;

	private LocalDateTime createDateTime;
	private LocalDateTime lastUpdateDateTime;
	private DataState dataState;
	
}
