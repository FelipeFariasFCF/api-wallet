package com.wallet.wallet_api.service;

import com.wallet.wallet_api.dto.TransactionRequestDTO;
import com.wallet.wallet_api.model.Transaction;

public interface TransactionService {
    Transaction createTransaction(TransactionRequestDTO dto);
}