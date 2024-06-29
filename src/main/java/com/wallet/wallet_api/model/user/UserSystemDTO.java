package com.wallet.wallet_api.model.user;

public record UserSystemDTO(
        String name,
        String email,
        String password
) {
}