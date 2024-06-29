package com.wallet.wallet_api.service;

import com.wallet.wallet_api.dto.WalletDetailsResponseDTO;
import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {

    Wallet createWallet(WalletRequestDTO dto);

    Page<Wallet> getWalletsByUser(Pageable pageable, String userId);

    WalletDetailsResponseDTO getWallet(String walletId);

    void deleteWallet(String walletId);
}