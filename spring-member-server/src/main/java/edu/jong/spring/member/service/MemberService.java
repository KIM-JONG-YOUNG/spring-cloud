package edu.jong.spring.member.service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;

@Validated
public interface MemberService {

	MemberDetails join(@NotNull @Valid MemberJoinParam param);
	
	MemberDetails modify(long no, @NotNull @Valid MemberModifyParam param);
	
	void remove(long no);
	
	MemberDetails get(long no);

	MemberDetails get(@NotBlank String username);

}
