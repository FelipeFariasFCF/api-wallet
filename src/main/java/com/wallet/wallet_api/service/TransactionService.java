package com.wallet.wallet_api.service;

import com.wallet.wallet_api.dto.TransactionDetailsResponseDTO;
import com.wallet.wallet_api.dto.TransactionRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    String createTransaction(TransactionRequestDTO dto);
    Page<TransactionDetailsResponseDTO> getTransactionsByWallet(Pageable pageable, String walletId);
    void deleteTransaction(String idTransaction);
}