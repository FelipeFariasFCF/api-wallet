package com.wallet.wallet_api.repository;

import com.wallet.wallet_api.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {
}