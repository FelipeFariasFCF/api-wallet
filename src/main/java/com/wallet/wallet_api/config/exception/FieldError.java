package com.wallet.wallet_api.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldError {

    private String fieldName;
    private String message;
}