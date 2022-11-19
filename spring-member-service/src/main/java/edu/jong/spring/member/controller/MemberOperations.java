package edu.jong.spring.member.controller;

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

@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MemberOperations {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> join(
			@RequestBody MemberJoinParam param);

	@PutMapping(value = "/{no}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modify(
			@PathVariable long no,
			@RequestBody MemberModifyParam param);

	@PostMapping(value = "/{no}")
	ResponseEntity<Void> restore(
			@PathVariable long no);

	@DeleteMapping(value = "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable long no);

	@GetMapping(value = "/{no}")
	ResponseEntity<MemberDetails> get(
			@PathVariable long no);

	@GetMapping(value = "/username/{username}")
	ResponseEntity<MemberDetails> get(
			@PathVariable String username);

	@GetMapping
	ResponseEntity<Void> list();

}
