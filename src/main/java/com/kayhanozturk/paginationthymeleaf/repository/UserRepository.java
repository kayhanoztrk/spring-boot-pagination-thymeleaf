package com.kayhanozturk.paginationthymeleaf.repository;

import com.kayhanozturk.paginationthymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
