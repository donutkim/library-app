package com.group.libraryapp.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = null;

	@Column(nullable = false, length = 20)
	private String name;

	private Integer age;

	//new
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

	protected User() {
	}

	public User(String name, Integer age) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어옴!", name));
		}
		this.age = age;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Long getId() {
		return id;
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void loanBook(String bookName) {
		this.userLoanHistories.add(new UserLoanHistory(this, bookName));
	}

	public void returnBook(String bookName) {
		UserLoanHistory targetHistory = this.userLoanHistories.stream()
			.filter(history -> history.getBookName().equals(bookName))
			.findFirst() //결과 optional
			.orElseThrow(IllegalStateException::new);
		targetHistory.doReturn();
	}
}
