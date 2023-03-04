package com.example.adorsys.repository;

import com.example.adorsys.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT t FROM User t WHERE t.active= :active ORDER BY t.id")
    Page<User> getPageByActive(@Param("active") boolean active, Pageable pageable);

    Optional<User> findByUsername(String username);

    Optional<User> findByActivationCode(String code);
}
