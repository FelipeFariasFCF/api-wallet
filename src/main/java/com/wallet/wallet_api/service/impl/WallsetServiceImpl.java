package com.wallet.wallet_api.service.impl;

import com.wallet.wallet_api.dto.WalletDetailsResponseDTO;
import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.dto.WalletResponseDTO;
import com.wallet.wallet_api.model.UserSystem;
import com.wallet.wallet_api.model.Wallet;
import com.wallet.wallet_api.repository.UserRepository;
import com.wallet.wallet_api.repository.WalletRepository;
import com.wallet.wallet_api.service.WalletService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WallsetServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public String createWallet(WalletRequestDTO dto) {
        UserSystem user = userRepository.findById(dto.userId()).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        Wallet wallet = new Wallet(dto, user);
        Wallet saved = walletRepository.save(wallet);
        return saved.getId();
    }

    @Override
    public Page<WalletResponseDTO> getWalletsByUser(Pageable pageable, String userId) {
        return walletRepository.findAllByUserSystem_IdOrderByInitialDate(pageable, userId).map(WalletResponseDTO::new);
    }

    @Override
    public WalletDetailsResponseDTO getWallet(String walletId) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new EntityNotFoundException("Wallet not found!"));
        return new WalletDetailsResponseDTO(wallet);
    }

    @Override
    public void deleteWallet(String walletId) {
        walletRepository.deleteById(walletId);
    }
}