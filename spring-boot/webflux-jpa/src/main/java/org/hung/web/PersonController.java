package org.hung.web;

import java.util.UUID;

import org.hung.pojo.Person;
import org.hung.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	@Autowired
	private PersonRepository repo;
	
	@GetMapping("/{id}")
	public Mono<Person> findById(@PathVariable String id) {
		return Mono.justOrEmpty(repo.findById(id));
		//return Mono.fromCallable(() -> repo.findById(id).get());
	}
	
	@GetMapping("/page")
	public Flux<Person> findByPage() {
		return Flux.fromIterable(repo.findAll());
	}
	
	@PostMapping("/")
	public Mono<Person> create(@RequestBody Person person) {
		person.setId(UUID.randomUUID().toString());
		Person result = repo.save(person);
		return Mono.just(result);
	}
	
	@PutMapping("/{id}")
	public Mono<Person> update(@PathVariable String id, @RequestBody Person person) {
		Person result = repo.save(person);
		return Mono.just(result);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Person> delete(@PathVariable String id) {
		Mono<Person> result = Mono.justOrEmpty(repo.findById(id));
		repo.deleteById(id);
		return result;
	}
}
