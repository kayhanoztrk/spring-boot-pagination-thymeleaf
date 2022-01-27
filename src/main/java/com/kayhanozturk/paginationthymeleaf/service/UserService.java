package com.kayhanozturk.paginationthymeleaf.service;

import com.kayhanozturk.paginationthymeleaf.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    User findById(Long id);

    User save(User user);
    User update(User user);
    void delete(Long id);
}
