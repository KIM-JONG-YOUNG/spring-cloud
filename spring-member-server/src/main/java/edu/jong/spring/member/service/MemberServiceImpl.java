package edu.jong.spring.member.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.jong.spring.common.enums.DataState;
import edu.jong.spring.member.entity.MemberEntity;
import edu.jong.spring.member.repository.MemberRepository;
import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final MemberRepository repository;
	private final PasswordEncoder encoder;
	
	@Transactional
	@Override
	public long join(@NotNull @Valid MemberJoinParam param) {
		
		if (repository.existsByUsername(param.getUsername()))
			throw new EntityExistsException("동일한 계정이 존재합니다.");
		
		MemberEntity member = repository.save(mapper.toEntity(param, encoder));
		
		return member.getNo();
	}

	@Override
	public long modify(long no, @NotNull @Valid MemberModifyParam param) {

		MemberEntity member = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
		
		repository.save(mapper.updateEntity(param, member));
		
		return member.getNo();
	}

	@Override
	public long restore(long no) {

		MemberEntity member = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
		member.setDataState(DataState.ACTIVE);
		repository.save(member);
		
		return member.getNo();
	}

	@Override
	public long remove(long no) {
		
		MemberEntity member = repository.findByIdForUpdate(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
		member.setDataState(DataState.DEACTIVE);
		repository.save(member);
		
		return member.getNo();
	}

	@Override
	public MemberDetails get(long no) {

		MemberEntity member = repository.findById(no)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));

		return mapper.toDetails(member);
	}

	@Override
	public MemberDetails get(String username) {

		MemberEntity member = repository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));

		return mapper.toDetails(member);
	}
}