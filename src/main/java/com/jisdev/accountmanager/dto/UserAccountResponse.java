package com.jisdev.accountmanager.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserAccountResponse {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
