package com.wallet.wallet_api.config.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class StandardError {

    private LocalDateTime timestamp;
    private String path;
    private Integer status;
    private String error;
    private String message;
}