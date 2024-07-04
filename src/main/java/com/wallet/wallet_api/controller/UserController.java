package com.wallet.wallet_api.controller;

import com.wallet.wallet_api.config.security.AccessToken;
import com.wallet.wallet_api.dto.LoginDTO;
import com.wallet.wallet_api.model.UserSystem;
import com.wallet.wallet_api.dto.UserSystemRequestDTO;
import com.wallet.wallet_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserSystemRequestDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        String userId = userService.createUser(new UserSystem(dto));
        URI uri = uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(userId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/auth")
    public ResponseEntity<AccessToken> authenticate(@RequestBody @Valid LoginDTO dto) {
        AccessToken token = userService.authenticate(dto.email(), dto.password());
        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable String idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }
}