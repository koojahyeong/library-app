package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne // 내가 다수이고 너가 한개
    private User user; //User가 UserLoanHistory와 직접 연결됨. 처음에 오류나는 이유는 UserLoanHistory의 테이블에 UserId만 존재

    private String bookName;

    private Boolean isReturn;


    //JPA 기본 생성자가 무조건 필요함
    protected UserLoanHistory() {

    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
