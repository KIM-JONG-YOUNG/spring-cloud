package edu.jong.spring.member.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.jong.spring.common.constants.CacheNames;
import edu.jong.spring.member.entity.MemberEntity;
import edu.jong.spring.member.repository.MemberRepository;
import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;
import edu.jong.spring.redis.service.RedisService;
import edu.jong.spring.role.client.RoleOperations;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final MemberRepository repository;
	private final PasswordEncoder encoder;
	private final RoleOperations roleOperations;
	private final RedisService redisService;

	private MemberDetails caching(MemberDetails details) {
		
		String cachingKey = CacheNames.MEMBER + details.getNo();
		redisService.caching(cachingKey, details, 60);
		
		return details;
	}
	
	@Transactional
	@Override
	public MemberDetails join(MemberJoinParam param) {
		
		if (repository.existsByUsername(param.getUsername()))
			throw new EntityExistsException("동일한 계정이 존재합니다.");
		
		MemberEntity savedMember = repository.save(mapper.toEntity(param, encoder));
		
		return caching(mapper.toDetails(savedMember));
	}

	@Transactional
	@Override
	public MemberDetails modify(long no, MemberModifyParam param) {

		MemberEntity member = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
		
		MemberEntity savedMember = repository.save(mapper.updateEntity(param, member));
		
		return caching(mapper.toDetails(savedMember));
	}

	@Transactional
	@Override
	public void remove(long no) {
		
		repository.delete(repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.")));
		
		ResponseEntity<Void> response = roleOperations.revokeAllToMember(no);
		
		if (response.getStatusCode() != HttpStatus.NO_CONTENT) 
			throw new RuntimeException("부여된 권한을 삭제하는데 실패했습니다.");

		redisService.remove(CacheNames.MEMBER + no);
	}

	@Transactional(readOnly = true)
	@Override
	public MemberDetails get(long no) {

		String cachingKey = CacheNames.ROLE + no;
		MemberDetails details = redisService.get(cachingKey, new TypeReference<MemberDetails>() {})
				.orElse(mapper.toDetails(repository.findById(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."))));

		return caching(details);
	}

	@Transactional(readOnly = true)
	@Override
	public MemberDetails get(String username) {
		return mapper.toDetails(repository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.")));
	}
}