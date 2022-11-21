package edu.jong.spring.member.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.jong.spring.member.client.MemberOperations;
import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;
import edu.jong.spring.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberOperations {

	private final MemberService service;
	
	@Override
	public ResponseEntity<Void> join(MemberJoinParam param) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + "/" + service.join(param))
				.build();
	}

	@Override
	public ResponseEntity<Void> modify(long no, MemberModifyParam param) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + "/" + service.modify(no, param))
				.build();
	}

	@Override
	public ResponseEntity<Void> restore(long no) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + "/" + service.restore(no))
				.build();
	}

	@Override
	public ResponseEntity<Void> remove(long no) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + "/" + service.remove(no))
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
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + "/" + service.get(username))
				.body(service.get(username));
	}

	@Override
	public ResponseEntity<Void> list() {
		return ResponseEntity.status(HttpStatus.OK)
				.build();
	}

}
