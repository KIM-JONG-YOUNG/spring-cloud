package edu.jong.spring.role.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jong.spring.domain.model.DataState;
import edu.jong.spring.role.model.RoleAddParam;
import edu.jong.spring.role.model.RoleDetails;
import edu.jong.spring.role.model.RoleEntity;
import edu.jong.spring.role.model.RoleModifyParam;
import edu.jong.spring.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleMapper mapper;
	private final RoleRepository repository;

	@Transactional
	@Override
	public long add(RoleAddParam param) {

		if (repository.existsByName(param.getName()))
			throw new EntityExistsException("동일한 권한명이 존재합니다.");
		
		RoleEntity role = repository.save(mapper.toEntity(param));
		return role.getNo();
	}

	@Transactional
	@Override
	public long modify(long no, RoleModifyParam param) {
		
		RoleEntity role = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		repository.save(mapper.updateEntity(param, role));
		
		return role.getNo();
	}

	@Transactional
	@Override
	public long restore(long no) {
		
		RoleEntity role = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		role.setDataState(DataState.ACTIVE);
		repository.save(role);
		
		return role.getNo();
	}

	@Transactional
	@Override
	public long remove(long no) {
		
		RoleEntity role = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		role.setDataState(DataState.DEACTIVE);
		repository.save(role);
		
		return role.getNo();

	}

	@Transactional(readOnly = true)
	@Override
	public RoleDetails get(long no) {
		
		RoleEntity role = repository.findById(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		
		return mapper.toDetails(role);
	}

}
