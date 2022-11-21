package edu.jong.spring.role.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.jong.spring.common.constants.APIUrls;
import edu.jong.spring.common.constants.CacheNames;
import edu.jong.spring.redis.service.RedisService;
import edu.jong.spring.role.client.RoleOperations;
import edu.jong.spring.role.request.RoleAddParam;
import edu.jong.spring.role.request.RoleModifyParam;
import edu.jong.spring.role.response.RoleDetails;
import edu.jong.spring.role.service.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleController  implements RoleOperations {

	private final RoleService service;
	
	@Override
	public ResponseEntity<RoleDetails> add(RoleAddParam param) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.add(param));
	}

	@Override
	public ResponseEntity<RoleDetails> modify(long no, RoleModifyParam param) {
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
	public ResponseEntity<RoleDetails> get(long no) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.get(no));
	}

	@Override
	public ResponseEntity<List<RoleDetails>> list() {
		// TODO Auto-generated method stub
		System.out.println("Role LIST");
		return null;
	}

	@Override
	public ResponseEntity<Void> grantToMember(long roleNo, long memberNo) {
		
		service.grantToMember(roleNo, memberNo);

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.build();
	}

	@Override
	public ResponseEntity<Void> revokeToMember(long roleNo, long memberNo) {

		service.revokeToMember(roleNo, memberNo);

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.build();
	}

	@Override
	public ResponseEntity<Void> revokeAllToMember(long memberNo) {

		service.revokeAllToMember(memberNo);

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.build();
	}

	@Override
	public ResponseEntity<List<RoleDetails>> getAllByMember(long memberNo) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(service.getAllByMember(memberNo));
	}
	
}
