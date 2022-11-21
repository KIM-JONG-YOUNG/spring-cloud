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
			value = APIUrls.ROLE_API_PREFIX,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> add(
			@RequestBody RoleAddParam param);
	
	@PutMapping(
			value = APIUrls.ROLE_API_PREFIX + "/{no}", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modify(
			@PathVariable("no") long no,
			@RequestBody RoleModifyParam param);

	@PostMapping(value = APIUrls.ROLE_API_PREFIX + "/{no}")
	ResponseEntity<Void> restore(
			@PathVariable("no") long no);

	@DeleteMapping(value = APIUrls.ROLE_API_PREFIX + "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable("no") long no);

	@GetMapping(value = APIUrls.ROLE_API_PREFIX + "/{no}")
	ResponseEntity<RoleDetails> get(
			@PathVariable("no") long no);

	@GetMapping(value = APIUrls.ROLE_API_PREFIX)
	ResponseEntity<List<RoleDetails>> list();
 
	@PostMapping(value = APIUrls.ROLE_API_PREFIX + "/{roleNo}/grant/{memberNo}")
	ResponseEntity<Void> grant(
			@PathVariable("roleNo") long roleNo,
			@PathVariable("memberNo") long memberNo);
	
	@DeleteMapping(value = APIUrls.ROLE_API_PREFIX + "/{roleNo}/revoke/{memberNo}")
	ResponseEntity<Void> revoke(
			@PathVariable("roleNo") long roleNo,
			@PathVariable("memberNo") long memberNo);
	
}
