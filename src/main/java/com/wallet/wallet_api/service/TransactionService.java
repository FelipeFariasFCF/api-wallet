package com.wallet.wallet_api.service;

import com.wallet.wallet_api.dto.TransactionRequestDTO;
import com.wallet.wallet_api.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    Transaction createTransaction(TransactionRequestDTO dto);

    Page<Transaction> getTransactionsByWallet(Pageable pageable, String walletId);
}