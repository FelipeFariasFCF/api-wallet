package com.wallet.wallet_api.model;

import com.wallet.wallet_api.dto.WalletRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDate initialDate;
    private LocalDate finishDate;
    private Double total = 0.00;

    @ManyToOne
    @JoinColumn(name = "user_system_id", nullable = false)
    private UserSystem userSystem;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public Wallet(WalletRequestDTO walletRequestDTO, UserSystem user) {
        this.name = walletRequestDTO.name();
        this.initialDate = walletRequestDTO.initialDate();
        this.finishDate = walletRequestDTO.finishDate();
        this.userSystem = user;
    }
}