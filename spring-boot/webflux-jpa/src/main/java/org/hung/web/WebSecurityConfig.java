package org.hung.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Bean
	public SecurityWebFilterChain actuatorSecurity(ServerHttpSecurity http) {
		http
		  .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
		  .authorizeExchange(exchange -> {
			  exchange.anyExchange().permitAll();
		  });
		return http.build();
	}
	
	@Bean
	public SecurityWebFilterChain webSecurity(ServerHttpSecurity http) {
		http.csrf().disable();
		http.authorizeExchange(exchange -> {
			exchange.anyExchange().authenticated();
		}).httpBasic();
		return http.build();
	}
}
