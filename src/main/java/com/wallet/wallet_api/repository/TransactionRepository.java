package com.wallet.wallet_api.repository;

import com.wallet.wallet_api.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Page<Transaction> getTransactionByWallet_Id(Pageable pageable, String walletId);
}