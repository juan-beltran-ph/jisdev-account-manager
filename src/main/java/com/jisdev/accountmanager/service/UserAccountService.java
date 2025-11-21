package com.jisdev.accountmanager.service;

import com.jisdev.accountmanager.dto.UserAccountRequest;
import com.jisdev.accountmanager.dto.UserAccountResponse;
import com.jisdev.accountmanager.exception.ResourceNotFoundException;
import com.jisdev.accountmanager.model.UserAccount;
import com.jisdev.accountmanager.repository.UserAccountRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountService implements IUserAccountService {

    private final UserAccountRepository repository;

    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccountResponse createUser(UserAccountRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserAccount user = UserAccount.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        UserAccount saved = repository.save(user);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccountResponse getUserById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserAccountResponse> listUsers(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    private UserAccountResponse toResponse(UserAccount user) {
        return UserAccountResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
