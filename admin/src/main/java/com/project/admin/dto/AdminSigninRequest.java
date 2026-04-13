package com.project.admin.dto;

import jakarta.validation.constraints.NotBlank;


public record AdminSigninRequest(

        @NotBlank
        String loginId,
        @NotBlank
        String password
) {
}
