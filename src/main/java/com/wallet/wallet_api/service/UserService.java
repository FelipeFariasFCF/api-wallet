package com.wallet.wallet_api.service;

import com.wallet.wallet_api.config.security.AccessToken;
import com.wallet.wallet_api.model.UserSystem;

public interface UserService {
    UserSystem getByEmail(String email);
    String createUser(UserSystem user);
    AccessToken authenticate(String email, String password);
    void deleteUser(String idUser);
}