package com.wallet.wallet_api.repository;

import com.wallet.wallet_api.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSystem, Long> {

    void deleteById(String id);

    Optional<UserSystem> findById(String id);

    Optional<UserSystem> findByEmail(String email);

    boolean existsByEmail(String email);
}