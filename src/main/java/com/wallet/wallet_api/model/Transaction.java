package com.wallet.wallet_api.model;

import com.wallet.wallet_api.dto.TransactionRequestDTO;
import com.wallet.wallet_api.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String description;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    public Transaction(TransactionRequestDTO dto, Wallet wallet) {
        this.name = dto.name();
        this.date = dto.date();
        this.type = TransactionType.valueOf(dto.type());
        this.description = dto.description();
        this.value = dto.value();
        this.wallet = wallet;
    }
}