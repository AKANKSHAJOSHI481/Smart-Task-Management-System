package com.stm.smart_task_management.auth.dto;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
