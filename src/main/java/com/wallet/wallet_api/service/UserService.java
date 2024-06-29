package com.wallet.wallet_api.service;

import com.wallet.wallet_api.config.security.AccessToken;
import com.wallet.wallet_api.model.user.UserSystem;

public interface UserService {
    UserSystem getByEmail(String email);
    UserSystem save(UserSystem user);
    AccessToken authenticate(String email, String password);
}