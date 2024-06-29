package com.wallet.wallet_api.model.user;

public record LoginDTO(
        String email,
        String password
) {
}