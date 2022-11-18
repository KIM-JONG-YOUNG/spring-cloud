package edu.jong.spring.role.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import edu.jong.spring.role.model.RoleAddParam;
import edu.jong.spring.role.model.RoleDetails;
import edu.jong.spring.role.model.RoleModifyParam;

@Validated
public interface RoleService {

	long add(@NotNull @Valid RoleAddParam param);
	
	long modify(long no,@NotNull @Valid RoleModifyParam param);
	
	long restore(long no);

	long remove(long no);

	RoleDetails get(long no);

}
