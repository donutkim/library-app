package com.group.libraryapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;

@Service
public class UserServiceV1 {

	private final UserJdbcRepository userJdbcRepository;

	public UserServiceV1(UserJdbcRepository userJdbcRepository) {
		// userRepository = new UserRepository(jdbcTemplate);
		this.userJdbcRepository = userJdbcRepository;
	}

	public void saveUser(UserCreateRequest request) {
		userJdbcRepository.saveUser(request.getName(), request.getAge());
	}

	public List<UserResponse> getUsers() {
		return userJdbcRepository.getUsers();
	}

	public void updateUser(UserUpdateRequest request) {
		// boolean isUserNotExist = userRepository.isUserNotExist(jdbcTemplate, request.getId());
		if (userJdbcRepository.isUserNotExist(request.getId())) {
			throw new IllegalArgumentException("no such user");
		}

		userJdbcRepository.updateUserName(request.getName(), request.getId());
	}

	public void deleteUser(String name) {

		if (userJdbcRepository.isUserNotExist(name)) {
			throw new IllegalArgumentException("no such user");
		}
		userJdbcRepository.deleteUser(name);
	}
}

