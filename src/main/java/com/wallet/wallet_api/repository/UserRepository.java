package com.wallet.wallet_api.repository;

import com.wallet.wallet_api.model.user.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSystem, Long> {

    UserSystem findByEmail(String email);

    Optional<UserSystem> getByEmail(String email);

    boolean existsByEmail(String email);
}