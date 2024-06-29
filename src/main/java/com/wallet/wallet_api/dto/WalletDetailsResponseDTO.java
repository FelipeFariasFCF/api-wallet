package com.wallet.wallet_api.dto;

import com.wallet.wallet_api.model.Wallet;

import java.time.LocalDate;
import java.util.List;

public record WalletDetailsResponseDTO(
        String name,
        LocalDate initialDate,
        LocalDate finishDate,
        Double total,
        List<TransactionResponseDTO> transactions
) {
    public WalletDetailsResponseDTO(Wallet wallet) {
        this(wallet.getName(),
                wallet.getInitialDate(),
                wallet.getFinishDate(),
                wallet.getTotal(),
                wallet.getTransactions().stream().map(TransactionResponseDTO::new).toList());
    }
}