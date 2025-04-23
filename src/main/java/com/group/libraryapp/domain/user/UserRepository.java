package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//JpaRepository 어떤 Entity를 다룰것인지?, primary key 타입은 무엇인지?
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByName(String name);
        boolean existsByName(String name);
}
