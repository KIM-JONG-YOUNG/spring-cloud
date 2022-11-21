package edu.jong.spring.member.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.jong.spring.common.constants.APIUrls;
import edu.jong.spring.common.constants.CacheNames;
import edu.jong.spring.member.client.MemberOperations;
import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;
import edu.jong.spring.member.service.MemberService;
import edu.jong.spring.redis.service.RedisService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberOperations {

	private final MemberService service;

	@Override
	public ResponseEntity<MemberDetails> join(MemberJoinParam param) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.join(param));
	}

	@Override
	public ResponseEntity<MemberDetails> modify(long no, MemberModifyParam param) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.modify(no, param));
	}

	@Override
	public ResponseEntity<Void> remove(long no) {
		service.remove(no);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.build();
	}

	@Override
	public ResponseEntity<MemberDetails> get(long no) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.get(no));
	}

	@Override
	public ResponseEntity<MemberDetails> get(String username) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.get(username));
	}

	@Override
	public ResponseEntity<Void> list() {
		return ResponseEntity.status(HttpStatus.OK)
				.build();
	}

}
