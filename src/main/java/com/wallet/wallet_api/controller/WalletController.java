package com.wallet.wallet_api.controller;

import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.model.Wallet;
import com.wallet.wallet_api.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid WalletRequestDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Wallet wallet = walletService.createWallet(dto);
        URI uri = uriComponentsBuilder.path("/v1/wallets/{id}").buildAndExpand(wallet.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}