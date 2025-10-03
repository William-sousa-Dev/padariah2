package com.javawilliam.padaria.dto;

import com.javawilliam.padaria.infrastructure.role.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role) {
}
