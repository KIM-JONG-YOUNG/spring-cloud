package edu.jong.spring.role.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jong.spring.common.enums.DataState;
import edu.jong.spring.member.client.MemberOperations;
import edu.jong.spring.member.response.MemberDetails;
import edu.jong.spring.role.entity.GrantedRoleEntity;
import edu.jong.spring.role.entity.RoleEntity;
import edu.jong.spring.role.repository.GrantedRoleRepository;
import edu.jong.spring.role.repository.RoleRepository;
import edu.jong.spring.role.request.RoleAddParam;
import edu.jong.spring.role.request.RoleModifyParam;
import edu.jong.spring.role.response.RoleDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleMapper mapper;
	private final RoleRepository roleRepository;
	private final GrantedRoleRepository grantedRoleRepository;
	private final MemberOperations memberOperations;
	
	@Transactional
	@Override
	public long add(RoleAddParam param) {

		if (roleRepository.existsByName(param.getName()))
			throw new EntityExistsException("동일한 권한명이 존재합니다.");
		RoleEntity role = roleRepository.save(mapper.toEntity(param));

		return role.getNo();
	}

	@Transactional
	@Override
	public long modify(long no, RoleModifyParam param) {
		
		RoleEntity role = roleRepository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		roleRepository.save(mapper.updateEntity(param, role));
		
		return role.getNo();
	}

	@Transactional
	@Override
	public long restore(long no) {
		
		RoleEntity role = roleRepository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		role.setDataState(DataState.ACTIVE);
		roleRepository.save(role);
		
		return role.getNo();
	}

	@Transactional
	@Override
	public long remove(long no) {
		
		RoleEntity role = roleRepository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		role.setDataState(DataState.DEACTIVE);
		roleRepository.save(role);
		
		return role.getNo();

	}

	@Transactional(readOnly = true)
	@Override
	public RoleDetails get(long no) {
		
		RoleEntity role = roleRepository.findById(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		
		return mapper.toDetails(role);
	}

	@Transactional
	@Override
	public void grant(long roleNo, long memberNo) {

		RoleEntity role = roleRepository.findByIdForUpdate(roleNo)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		
		MemberDetails member = memberOperations.get(memberNo).getBody();
		
		if (member == null) throw new EntityNotFoundException("사용자가 존재하지 않습니다.");
		
		grantedRoleRepository.save(GrantedRoleEntity.builder()
				.roleNo(role.getNo())
				.memberNo(member.getNo())
				.build());
	}

	@Transactional
	@Override
	public void revoke(long roleNo, long memberNo) {
		grantedRoleRepository.delete(
				grantedRoleRepository.findByIdForUpdate(roleNo, memberNo)
					.orElseThrow(() -> new EntityNotFoundException("부여된 권한이 존재하지 않습니다.")));
	}

}
