package com.wallet.wallet_api.service;

import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.model.Wallet;

public interface WalletService {

    Wallet createWallet(WalletRequestDTO dto);
}