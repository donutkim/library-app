package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse {

	private long id;
	private String name;
	private Integer age;

	public UserResponse(long id, String name, Integer age) {
		this.age = age;
		this.id = id;
		this.name = name;
	}

	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.age = user.getAge();
	}

	public UserResponse(long id, User user) {
		this.id = id;
		this.name = user.getName();
		this.age = user.getAge();
	}

	public Integer getAge() {
		return age;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
