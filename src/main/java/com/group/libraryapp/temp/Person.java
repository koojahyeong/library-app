package com.group.libraryapp.temp;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Address address;
 
    // 연관관계 주인 효과 Address에서 setPerosn을 하면 반영이 안된다
    // 상대 테이블을 참조하고 있으면 연관관계 주인
    // 연관관계의 주인이 아니면 mappedBy
    // 연관관계의 주인의 setter을 설정

    public void setAddress(Address address) {
        this.address = address;
        this.address.setPerson(this);
        // person이 address를 갖는 동시에 거꾸로도
    }

}
