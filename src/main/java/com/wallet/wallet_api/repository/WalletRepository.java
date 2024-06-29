package com.wallet.wallet_api.repository;

import com.wallet.wallet_api.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String> {

    Optional<Wallet> findById(String id);
    Page<Wallet> findAllByUserSystem_IdOrderByInitialDate(Pageable pageable, String idUser);
}