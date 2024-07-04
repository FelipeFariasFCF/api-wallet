package com.wallet.wallet_api.service;

import com.wallet.wallet_api.dto.WalletDetailsResponseDTO;
import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.dto.WalletResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    String createWallet(WalletRequestDTO dto);
    Page<WalletResponseDTO> getWalletsByUser(Pageable pageable, String userId);
    WalletDetailsResponseDTO getWallet(String walletId);
    void deleteWallet(String walletId);
}