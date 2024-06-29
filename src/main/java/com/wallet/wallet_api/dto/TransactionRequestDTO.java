package com.wallet.wallet_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record TransactionRequestDTO(

        @NotBlank
        String name,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate date,

        @NotBlank(message = "Must be either CREDIT or DEBIT")
        String type,

        String description,

        @NotNull
        @Positive
        Double value,

        @NotBlank
        String walletId
) {
}