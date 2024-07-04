package com.wallet.wallet_api.service.impl;

import com.wallet.wallet_api.dto.TransactionDetailsResponseDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public String createTransaction(TransactionRequestDTO dto) {
        Wallet wallet = walletRepository.findById(dto.walletId()).orElseThrow(() -> new EntityNotFoundException("Wallet not found!"));
        wallet.updateWalletBalance(TransactionType.valueOf(dto.type()), dto.value());
        Transaction transaction = new Transaction(dto, wallet);
        Transaction saved = transactionRepository.save(transaction);
        return saved.getId();
    }

    @Override
    public Page<TransactionDetailsResponseDTO> getTransactionsByWallet(Pageable pageable, String walletId) {
        return transactionRepository.getTransactionByWallet_Id(pageable, walletId).map(TransactionDetailsResponseDTO::new);
    }

    @Override
    public void deleteTransaction(String idTransaction) {
        transactionRepository.deleteById(idTransaction);
    }
}