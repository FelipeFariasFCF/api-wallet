package com.wallet.wallet_api.controller;

import com.wallet.wallet_api.config.security.AccessToken;
import com.wallet.wallet_api.model.user.LoginDTO;
import com.wallet.wallet_api.model.user.UserSystem;
import com.wallet.wallet_api.model.user.UserSystemDTO;
import com.wallet.wallet_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UserSystemDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        UserSystem user = userService.save(new UserSystem(dto));
        URI uri = uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/auth")
    public ResponseEntity<AccessToken> authenticate(@RequestBody LoginDTO dto) {
        AccessToken token = userService.authenticate(dto.email(), dto.password());
        return ResponseEntity.ok(token);
    }
}