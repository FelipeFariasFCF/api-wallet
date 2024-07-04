package com.wallet.wallet_api.service.impl;

import com.wallet.wallet_api.config.security.AccessToken;
import com.wallet.wallet_api.config.security.JwtService;
import com.wallet.wallet_api.config.exception.DuplicatedTupleException;
import com.wallet.wallet_api.config.exception.UnauthorizedException;
import com.wallet.wallet_api.model.UserSystem;
import com.wallet.wallet_api.repository.UserRepository;
import com.wallet.wallet_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserSystem getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Override
    public String createUser(UserSystem user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new DuplicatedTupleException("User already exists!");
        }
        encodePassword(user);
        UserSystem saved = userRepository.save(user);
        return saved.getId();
    }

    @Override
    public AccessToken authenticate(String email, String password) {
        UserSystem user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (matches) {
            return jwtService.generateAccessToken(user);
        }
        throw new UnauthorizedException("Unable to find an account that matches what you entered!");
    }

    @Override
    @Transactional
    public void deleteUser(String idUser) {
        userRepository.deleteById(idUser);
    }

    private void encodePassword(UserSystem user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
    }
}