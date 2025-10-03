package com.javawilliam.padaria.infrastructure.role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static UserRole fromValue(String value) {
        if (value == null || value.isBlank()) {
            return null; // pode retornar null se o campo não vier preenchido
        }
        for (UserRole role : UserRole.values()) {
            if (role.role.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
