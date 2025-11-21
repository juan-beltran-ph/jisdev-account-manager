package com.jisdev.accountmanager.controller;

import com.jisdev.accountmanager.dto.UserAccountRequest;
import com.jisdev.accountmanager.dto.UserAccountResponse;
import com.jisdev.accountmanager.service.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/accounts")
public class UserAccountController {

    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserAccountResponse> createUser(@Valid @RequestBody UserAccountRequest request) {
        UserAccountResponse created = service.createUser(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserAccountResponse>> listUsers(Pageable pageable) {
        return ResponseEntity.ok(service.listUsers(pageable));
    }
}
