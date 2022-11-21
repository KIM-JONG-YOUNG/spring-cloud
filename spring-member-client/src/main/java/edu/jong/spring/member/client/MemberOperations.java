package edu.jong.spring.member.client;

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
import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;

@FeignClient(name = ServiceNames.MEMBER_SERVICE)
public interface MemberOperations {

	@PostMapping(value= APIUrls.MEMBERS, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MemberDetails> join(
			@RequestBody MemberJoinParam param);

	@PutMapping(value = APIUrls.MEMBERS + "/{no}", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MemberDetails> modify(
			@PathVariable long no,
			@RequestBody MemberModifyParam param);

	@DeleteMapping(value = APIUrls.MEMBERS + "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable long no);

	@GetMapping(value = APIUrls.MEMBERS + "/{no}")
	ResponseEntity<MemberDetails> get(
			@PathVariable long no);

	@GetMapping(value = APIUrls.MEMBERS + "/username/{username}")
	ResponseEntity<MemberDetails> get(
			@PathVariable String username);

	@GetMapping(value = APIUrls.MEMBERS)
	ResponseEntity<Void> list();

}
