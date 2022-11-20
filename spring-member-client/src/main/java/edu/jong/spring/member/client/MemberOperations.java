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
import org.springframework.web.bind.annotation.RequestMapping;

import edu.jong.spring.member.model.MemberDetails;
import edu.jong.spring.member.model.MemberJoinParam;
import edu.jong.spring.member.model.MemberModifyParam;

@FeignClient("member-service")
public interface MemberOperations {

	public static final String CONTEXT_PATH = "/members";
	
	@PostMapping(value= CONTEXT_PATH, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> join(
			@RequestBody MemberJoinParam param);

	@PutMapping(value = CONTEXT_PATH + "/{no}", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modify(
			@PathVariable long no,
			@RequestBody MemberModifyParam param);

	@PostMapping(value = CONTEXT_PATH + "/{no}")
	ResponseEntity<Void> restore(
			@PathVariable long no);

	@DeleteMapping(value = CONTEXT_PATH + "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable long no);

	@GetMapping(value = CONTEXT_PATH + "/{no}")
	ResponseEntity<MemberDetails> get(
			@PathVariable long no);

	@GetMapping(value = CONTEXT_PATH + "/username/{username}")
	ResponseEntity<MemberDetails> get(
			@PathVariable String username);

	@GetMapping(value = CONTEXT_PATH)
	ResponseEntity<Void> list();

}
