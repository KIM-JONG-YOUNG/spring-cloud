package edu.jong.spring.role.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.jong.spring.role.model.RoleAddParam;
import edu.jong.spring.role.model.RoleDetails;
import edu.jong.spring.role.model.RoleModifyParam;

@FeignClient("role-service")
public interface RoleOperations {

	public static final String CONTEXT_PATH = "/roles";
	
	@PostMapping(
			value = CONTEXT_PATH,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> add(
			@RequestBody RoleAddParam param);
	
	@PutMapping(
			value = CONTEXT_PATH + "/{no}", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modify(
			@PathVariable("no") long no,
			@RequestBody RoleModifyParam param);

	@PostMapping(value = CONTEXT_PATH + "/{no}")
	ResponseEntity<Void> restore(
			@PathVariable("no") long no);

	@DeleteMapping(value = CONTEXT_PATH + "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable("no") long no);

	@GetMapping(value = CONTEXT_PATH + "/{no}")
	ResponseEntity<RoleDetails> get(
			@PathVariable("no") long no);

	@GetMapping(value = CONTEXT_PATH)
	ResponseEntity<List<RoleDetails>> list();
 
	@PostMapping(value = "/{roleNo}/{memberNo}")
	ResponseEntity<Void> grant(
			@PathVariable("roleNo") long roleNo,
			@PathVariable("memberNo") long memberNo);
	
	@DeleteMapping(value = "/{roleNo}/{memberNo}")
	ResponseEntity<Void> revoke(
			@PathVariable("roleNo") long roleNo,
			@PathVariable("memberNo") long memberNo);
	
}
