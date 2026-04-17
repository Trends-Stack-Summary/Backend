package com.project.admin.domain.controller.dto;

import jakarta.validation.constraints.NotBlank;


public record AdminSignupRequest(
        @NotBlank(message = "{error.signup.name.notBlank}")
        String name,
        @NotBlank(message = "{error.signup.loginId.notBlank}")
        String loginId,
        @NotBlank(message = "{error.signup.password.notBlank}")
        String password
) {
}

