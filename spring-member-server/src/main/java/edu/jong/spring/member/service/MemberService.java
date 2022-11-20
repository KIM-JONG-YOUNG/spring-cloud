package edu.jong.spring.member.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import edu.jong.spring.member.model.MemberDetails;
import edu.jong.spring.member.model.MemberJoinParam;
import edu.jong.spring.member.model.MemberModifyParam;

@Validated
public interface MemberService {

	long join(@NotNull @Valid MemberJoinParam param);
	
	long modify(long no, @NotNull @Valid MemberModifyParam param);
	
	long restore(long no);

	long remove(long no);
	
	MemberDetails get(long no);

	MemberDetails get(String username);

}
