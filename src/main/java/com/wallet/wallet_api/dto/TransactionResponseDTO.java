package com.wallet.wallet_api.dto;

import com.wallet.wallet_api.model.Transaction;
import com.wallet.wallet_api.model.enums.TransactionType;

public record TransactionResponseDTO(
        String name,
        TransactionType type,
        Double value
) {
    public TransactionResponseDTO(Transaction transaction) {
        this(transaction.getName(), transaction.getType(), transaction.getValue());
    }
}