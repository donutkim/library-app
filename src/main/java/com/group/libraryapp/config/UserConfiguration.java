package com.group.libraryapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.group.libraryapp.repository.user.UserJdbcRepository;

@Configuration
public class UserConfiguration {

	@Bean
	public UserJdbcRepository userJdbcRepository(JdbcTemplate jdbcTemplate) {
		return new UserJdbcRepository(jdbcTemplate);
	}
}
