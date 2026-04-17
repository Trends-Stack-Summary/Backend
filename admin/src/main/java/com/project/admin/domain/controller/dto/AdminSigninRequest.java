package com.project.admin.domain.controller.dto;

import jakarta.validation.constraints.NotBlank;


public record AdminSigninRequest(

        @NotBlank(message = "{error.signin.loginId.notBlank}")
        String loginId,
        @NotBlank(message = "{error.signin.password.notBlank}")
        String password
) {
}
