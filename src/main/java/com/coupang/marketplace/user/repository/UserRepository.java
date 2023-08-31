package com.coupang.marketplace.user.repository;

import com.coupang.marketplace.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@Mapper 추가 예정
public interface UserRepository {

    void insertUser(User user);
    Optional<User> findByEmail(String email);


}
