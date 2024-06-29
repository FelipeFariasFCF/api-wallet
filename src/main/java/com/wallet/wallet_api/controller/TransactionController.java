package com.wallet.wallet_api.controller;

import com.wallet.wallet_api.dto.TransactionDetailsResponseDTO;
import com.wallet.wallet_api.dto.TransactionRequestDTO;
import com.wallet.wallet_api.model.Transaction;
import com.wallet.wallet_api.service.TransactionService;
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
@RequestMapping("/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid TransactionRequestDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        Transaction transaction = transactionService.createTransaction(dto);
        URI uri = uriComponentsBuilder.path("/v1/wallets/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/by-wallet/{walletId}")
    ResponseEntity<Page<TransactionDetailsResponseDTO>> getByTransactionId(@PathVariable String walletId, Pageable pageable) {
        return ResponseEntity.ok().body(transactionService.getTransactionsByWallet(pageable, walletId).map(TransactionDetailsResponseDTO::new));
    }

    @DeleteMapping("/{transactionId}")
    ResponseEntity<Void> delete(@PathVariable String transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}