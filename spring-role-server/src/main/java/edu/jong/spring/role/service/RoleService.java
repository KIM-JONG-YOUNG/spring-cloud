package edu.jong.spring.role.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import edu.jong.spring.role.request.RoleAddParam;
import edu.jong.spring.role.request.RoleModifyParam;
import edu.jong.spring.role.response.RoleDetails;

@Validated
public interface RoleService {

	RoleDetails add(@NotNull @Valid RoleAddParam param);
	
	RoleDetails modify(long no,@NotNull @Valid RoleModifyParam param);
	
	void remove(long no);

	RoleDetails get(long no);

	void grantToMember(long roleNo, long memberNo);

	void revokeToMember(long roleNo, long memberNo);

	void revokeAllToMember(long memberNo);

	List<RoleDetails> getAllByMember(long memberNo);
}
