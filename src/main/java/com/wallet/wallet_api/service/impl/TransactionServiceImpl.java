package com.wallet.wallet_api.service.impl;

import com.wallet.wallet_api.dto.TransactionRequestDTO;
import com.wallet.wallet_api.model.Transaction;
import com.wallet.wallet_api.model.Wallet;
import com.wallet.wallet_api.model.enums.TransactionType;
import com.wallet.wallet_api.repository.TransactionRepository;
import com.wallet.wallet_api.repository.WalletRepository;
import com.wallet.wallet_api.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public Transaction createTransaction(TransactionRequestDTO dto) {
        Wallet wallet = walletRepository.findById(dto.walletId()).orElseThrow(() -> new EntityNotFoundException("Wallet not found!"));
        wallet.updateWalletBalance(TransactionType.valueOf(dto.type()), dto.value());
        Transaction transaction = new Transaction(dto, wallet);
        return transactionRepository.save(transaction);
    }
}