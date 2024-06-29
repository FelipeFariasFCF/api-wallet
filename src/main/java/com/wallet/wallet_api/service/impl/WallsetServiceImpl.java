package com.wallet.wallet_api.service.impl;

import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.model.UserSystem;
import com.wallet.wallet_api.model.Wallet;
import com.wallet.wallet_api.repository.UserRepository;
import com.wallet.wallet_api.repository.WalletRepository;
import com.wallet.wallet_api.service.WalletService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WallsetServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public Wallet createWallet(WalletRequestDTO dto) {
        UserSystem user = userRepository.findById(dto.userId()).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        Wallet wallet = new Wallet(dto, user);
        return walletRepository.save(wallet);
    }
}
