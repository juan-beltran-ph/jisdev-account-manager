package com.jisdev.accountmanager.service;

import com.jisdev.accountmanager.dto.UserAccountRequest;
import com.jisdev.accountmanager.dto.UserAccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserAccountService {
    UserAccountResponse createUser(UserAccountRequest request);
    UserAccountResponse getUserById(Long id);
    Page<UserAccountResponse> listUsers(Pageable pageable);
}
