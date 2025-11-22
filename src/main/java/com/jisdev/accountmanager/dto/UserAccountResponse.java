package com.jisdev.accountmanager.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class UserAccountResponse {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
