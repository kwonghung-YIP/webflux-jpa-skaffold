package org.hung.web;

import java.util.Optional;
import java.util.UUID;

import org.hung.pojo.Person;
import org.hung.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@RequestMapping("/api/v1/person")
public class PersonController {

	@Autowired
	private PersonRepository repo;
	
	@GetMapping("/{id}")
	public ResponseEntity<Mono<Person>> findById(@PathVariable String id) {
		Optional<Person> result = repo.findById(id);
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.justOrEmpty(result));
		}
	}
	
	@GetMapping("/page")
	public ResponseEntity<Flux<Person>> findByPage() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Flux.fromIterable(repo.findAll()));
	}
	
	@PostMapping("/")
	public ResponseEntity<Mono<Person>> create(@RequestBody Person person) {
		person.setId(UUID.randomUUID().toString());
		Person result = repo.save(person);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(result));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mono<Person>> update(@PathVariable String id, @RequestBody Person person) {
		Optional<Person> found = repo.findById(id);
		if (found.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			Person result = repo.save(person);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(result));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Mono<Person>> delete(@PathVariable String id) {
		Optional<Person> found = repo.findById(id);
		if (found.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			repo.deleteById(id);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.justOrEmpty(found));
		}
	}
}
