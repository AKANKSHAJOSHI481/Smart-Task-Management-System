package com.stm.smart_task_management.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
