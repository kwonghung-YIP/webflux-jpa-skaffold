package org.hung.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class EchoController {

	@GetMapping("/echo")
	public Mono<String> echo() {
		return Mono.just("Hello World!");
	}
}
