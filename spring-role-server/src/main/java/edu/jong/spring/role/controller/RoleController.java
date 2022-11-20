package edu.jong.spring.role.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.jong.spring.role.client.RoleOperations;
import edu.jong.spring.role.model.RoleAddParam;
import edu.jong.spring.role.model.RoleDetails;
import edu.jong.spring.role.model.RoleModifyParam;
import edu.jong.spring.role.service.RoleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoleController  implements RoleOperations {

	private final RoleService service;
	
	@Override
	public ResponseEntity<Void> add(RoleAddParam param) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + "/" + service.add(param))
				.build();
	}

	@Override
	public ResponseEntity<Void> modify(long no, RoleModifyParam param) {
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
	public ResponseEntity<Void> grant(long roleNo, long memberNo) {
		
		service.grant(roleNo, memberNo);

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + String.format("/%s/%s", roleNo, memberNo))
				.build();
	}

	@Override
	public ResponseEntity<Void> revoke(long roleNo, long memberNo) {

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header(HttpHeaders.LOCATION, CONTEXT_PATH + String.format("/%s/%s", roleNo, memberNo))
				.build();
	}
	
}
