package com.wallet.wallet_api.dto;

import com.wallet.wallet_api.model.Transaction;
import com.wallet.wallet_api.model.enums.TransactionType;

import java.time.LocalDate;

public record TransactionDetailsResponseDTO(
        String name,
        LocalDate date,
        TransactionType type,
        String description,
        Double value
) {
    public TransactionDetailsResponseDTO(Transaction transaction) {
        this(transaction.getName(), transaction.getDate(), transaction.getType(), transaction.getDescription(), transaction.getValue());
    }
}