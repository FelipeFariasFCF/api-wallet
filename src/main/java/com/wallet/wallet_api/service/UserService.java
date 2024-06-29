package com.wallet.wallet_api.service;

import com.wallet.wallet_api.config.security.AccessToken;
import com.wallet.wallet_api.model.UserSystem;

public interface UserService {
    UserSystem save(UserSystem user);
    AccessToken authenticate(String email, String password);
}