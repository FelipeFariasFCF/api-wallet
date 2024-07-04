package com.wallet.wallet_api.controller;

import com.wallet.wallet_api.dto.WalletDetailsResponseDTO;
import com.wallet.wallet_api.dto.WalletRequestDTO;
import com.wallet.wallet_api.dto.WalletResponseDTO;
import com.wallet.wallet_api.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid WalletRequestDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        String walletId = walletService.createWallet(dto);
        URI uri = uriComponentsBuilder.path("/v1/wallets/{id}").buildAndExpand(walletId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Page<WalletResponseDTO>> getAllByUserId(@PathVariable String userId, Pageable pageable) {
        return ResponseEntity.ok().body(walletService.getWalletsByUser(pageable, userId));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletDetailsResponseDTO> getByWalletId(@PathVariable String walletId) {
        return ResponseEntity.ok().body(walletService.getWallet(walletId));
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Void> delete(@PathVariable String walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }
}