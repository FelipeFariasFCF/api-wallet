package com.wallet.wallet_api.model;

import com.wallet.wallet_api.dto.UserSystemDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "userSystem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wallet> wallets;

    public UserSystem(UserSystemDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
    }
}