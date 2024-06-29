package com.wallet.wallet_api.dto;

import com.wallet.wallet_api.model.Wallet;

public record WalletResponseDTO(
        String name,
        Double total
) {
    public WalletResponseDTO(Wallet wallet) {
        this(wallet.getName(), wallet.getTotal());
    }
}