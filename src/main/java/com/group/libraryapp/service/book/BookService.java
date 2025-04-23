package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import com.group.libraryapp.repository.book.BookMemoryRepository;
import com.group.libraryapp.repository.book.BookMySqlRepository;
//import com.group.libraryapp.repository.book.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    //interface를 활용하면 어떤 '구현체' MySql Memory 타입의 인스턴스를 쓸건지만 보여주면 된다
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;



    public BookService(BookRepository bookRepository,
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
        // 1. 책 정보를 가져온다

        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출 중인지 확인다
        // 3. 대출 중이라면 예외를 발생시킨다

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("대출되어 있는 책입니다");
        }

        // 4. 유저 정보를 가져온다
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);


        // 5.1 User 내에서 loanBook
        user.loanBook(book.getName());

        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
//        userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));

    }
    @Transactional
    public void returnBook(BookReturnRequest request) {
        //0. 유저 찾기
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        //1.1. 유저내 returnBook() 호출
        user.returnBook(request.getBookName());

        //1. 대출 정보를 가져온다
//        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(),request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//        history.doReturn();
        //2. 대출 정보가 있다면 지워준다.


    }

}
