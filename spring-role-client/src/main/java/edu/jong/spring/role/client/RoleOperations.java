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

import edu.jong.spring.common.constants.APIUrls;
import edu.jong.spring.common.constants.ServiceNames;
import edu.jong.spring.role.request.RoleAddParam;
import edu.jong.spring.role.request.RoleModifyParam;
import edu.jong.spring.role.response.RoleDetails;

@FeignClient(name = ServiceNames.ROLE_SERVICE)
public interface RoleOperations {

	@PostMapping(
			value = APIUrls.ROLES,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<RoleDetails> add(
			@RequestBody RoleAddParam param);
	
	@PutMapping(
			value = APIUrls.ROLES + "/{no}", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<RoleDetails> modify(
			@PathVariable("no") long no,
			@RequestBody RoleModifyParam param);

	@DeleteMapping(value = APIUrls.ROLES + "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable("no") long no);

	@GetMapping(value = APIUrls.ROLES + "/{no}")
	ResponseEntity<RoleDetails> get(
			@PathVariable("no") long no);

	@GetMapping(value = APIUrls.ROLES)
	ResponseEntity<List<RoleDetails>> list();
 
	@PostMapping(value = APIUrls.ROLES + "/{roleNo}/grant/{memberNo}")
	ResponseEntity<Void> grantToMember(
			@PathVariable("roleNo") long roleNo,
			@PathVariable("memberNo") long memberNo);
	
	@DeleteMapping(value = APIUrls.ROLES + "/{roleNo}/revoke/{memberNo}")
	ResponseEntity<Void> revokeToMember(
			@PathVariable("roleNo") long roleNo,
			@PathVariable("memberNo") long memberNo);

	@DeleteMapping(value = APIUrls.ROLES + "/revokeAll/{memberNo}")
	ResponseEntity<Void> revokeAllToMember(
			@PathVariable("memberNo") long memberNo);

	@GetMapping(value = APIUrls.ROLES + "/grant/{memberNo}")
	ResponseEntity<List<RoleDetails>> getAllByMember(
			@PathVariable("memberNo") long memberNo);

	
}
