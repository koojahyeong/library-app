package com.group.libraryapp.domain.book;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // @Column(nullable = false, length =255 기본값, name = "name" 테이블 name이 필드이름 name) 인데
    @Column(nullable = false)
    private String name;

    // JPA의 경우 기본생성자가 하나 필요
    protected Book() {

    }

    public Book(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%S)이 들어왔습니다.",name));
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
