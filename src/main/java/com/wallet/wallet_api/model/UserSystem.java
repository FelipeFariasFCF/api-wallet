package com.wallet.wallet_api.model;

import com.wallet.wallet_api.dto.UserSystemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

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

    public UserSystem (UserSystemDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
    }
}