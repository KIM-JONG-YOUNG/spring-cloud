package edu.jong.spring.role.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.querydsl.jpa.impl.JPAQueryFactory;

import edu.jong.spring.common.constants.CacheNames;
import edu.jong.spring.member.client.MemberOperations;
import edu.jong.spring.member.response.MemberDetails;
import edu.jong.spring.redis.service.RedisService;
import edu.jong.spring.role.entity.GrantedRoleEntity;
import edu.jong.spring.role.entity.QGrantedRoleEntity;
import edu.jong.spring.role.entity.QRoleEntity;
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
	private final RedisService redisService;
	
	private final JPAQueryFactory jpaQueryFactory;
	private final QGrantedRoleEntity grantedRole = QGrantedRoleEntity.grantedRoleEntity;
	private final QRoleEntity role = QRoleEntity.roleEntity;
	
	private RoleDetails caching(RoleDetails details) {
		
		String cachingKey = CacheNames.ROLE + details.getNo();
		redisService.caching(cachingKey, details, 60);
		
		return details;
	}
	 
	@Transactional
	@Override
	public RoleDetails add(RoleAddParam param) {

		if (roleRepository.existsByName(param.getName()))
			throw new EntityExistsException("동일한 권한명이 존재합니다.");

		RoleEntity savedRole = roleRepository.save(mapper.toEntity(param));

		return caching(mapper.toDetails(savedRole));
	}

	@Transactional
	@Override
	public RoleDetails modify(long no, RoleModifyParam param) {
		
		RoleEntity role = roleRepository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."));
		RoleEntity savedRole = roleRepository.save(mapper.updateEntity(param, role));
		
		return caching(mapper.toDetails(savedRole));
	}

	@Transactional
	@Override
	public void remove(long no) {
		
		roleRepository.delete(roleRepository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다.")));
		
		grantedRoleRepository.deleteAll(
				grantedRoleRepository.findAllByRoleNoForUpdate(no));
		
		redisService.remove(CacheNames.ROLE + no);
	}

	@Transactional(readOnly = true)
	@Override
	public RoleDetails get(long no) {
		
		String cachingKey = CacheNames.ROLE + no;
		RoleDetails details = redisService.get(cachingKey, new TypeReference<RoleDetails>() {})
				.orElse(mapper.toDetails(roleRepository.findById(no)
				.orElseThrow(() -> new EntityNotFoundException("권한이 존재하지 않습니다."))));

		return caching(details);
	}

	@Transactional
	@Override
	public void grantToMember(long roleNo, long memberNo) {

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
	public void revokeToMember(long roleNo, long memberNo) {
		grantedRoleRepository.delete(grantedRoleRepository.findByIdForUpdate(roleNo, memberNo)
				.orElseThrow(() -> new EntityNotFoundException("부여된 권한이 존재하지 않습니다.")));
	}

	@Transactional
	@Override
	public void revokeAllToMember(long memberNo) {
		grantedRoleRepository.deleteAll(
				grantedRoleRepository.findAllByMemberNoForUpdate(memberNo));
	}

	@Transactional(readOnly = true)
	@Override
	public List<RoleDetails> getAllByMember(long memberNo) {
		TypeReference<List<RoleDetails>> type = new TypeReference<List<RoleDetails>>() {};
		return redisService.get(CacheNames.GRANTED_ROLE + memberNo, type)
			.orElseGet(() -> {
				
				List<RoleEntity> roleList = jpaQueryFactory.select(role)
						.from(grantedRole)
						.join(role)
						.on(grantedRole.roleNo.eq(role.no))
						.fetch();

				return roleList.stream()
						.map(x -> mapper.toDetails(x))
						.collect(Collectors.toList());
			});
		
	}

}
