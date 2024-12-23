package com.group.libraryapp.service.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final UserLoanHistoryRepository userLoanHistoryRepository;
	private final UserRepository userRepository;

	public BookService(
		BookRepository bookRepository,
		UserLoanHistoryRepository userLoanHistoryRepository,
		UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userLoanHistoryRepository = userLoanHistoryRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveBook(BookCreateRequest request) {
		bookRepository.save(new Book(request.getName()));
	}

	@Transactional
	public void loanBook(BookLoanRequest request) {
		//1.책 정보 가져오기
		Book book = bookRepository.findByName(request.getBookName())
			.orElseThrow(IllegalArgumentException::new);

		//2.대출 기록에서 이 책이 대출중인지 확인
		//3.만약 재출중이라면 예외 발생
		if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
			throw new IllegalArgumentException("Book already loaned");
		}

		//4.유저 정보 가져오기
		User user = userRepository.findByName(request.getUserName())
			.orElseThrow(IllegalArgumentException::new);

		//5.유저 정보와 책 정보를 기반으로 UserLoanHistory 를 저장
		// userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
		user.loanBook(book.getName());
	}


	@Transactional
	public void returnBook(BookReturnRequest request) {
		User user = userRepository.findByName(request.getUserName())
			.orElseThrow(IllegalArgumentException::new);

		// UserLoanHistory history = userLoanHistoryRepository
		// 	.findByUserIdAndBookName(user.getId(), request.getBookName())
		// 	.orElseThrow(IllegalArgumentException::new);
		//
		// history.doReturn();
		//
		// userLoanHistoryRepository.save(history);

		user.returnBook(request.getBookName());
	}
}
