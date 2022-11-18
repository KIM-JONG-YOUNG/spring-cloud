package edu.jong.spring.role.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.jong.spring.role.model.RoleAddParam;
import edu.jong.spring.role.model.RoleDetails;
import edu.jong.spring.role.model.RoleModifyParam;

@RequestMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public interface RoleOperations {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> add(
			@RequestBody RoleAddParam param);
	
	@PutMapping(value = "/{no}", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> modify(
			@PathVariable("no") long no,
			@RequestBody RoleModifyParam param);

	@PostMapping(value = "/{no}")
	ResponseEntity<Void> restore(
			@PathVariable("no") long no);

	@DeleteMapping(value = "/{no}")
	ResponseEntity<Void> remove(
			@PathVariable("no") long no);

	@GetMapping(value = "/{no}")
	ResponseEntity<RoleDetails> get(
			@PathVariable("no") long no);

	@GetMapping
	ResponseEntity<List<RoleDetails>> list();

}
