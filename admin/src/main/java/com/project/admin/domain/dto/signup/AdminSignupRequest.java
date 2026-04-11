package com.project.admin.domain.dto.signup;

import jakarta.validation.constraints.NotBlank;


public record AdminSignupRequest (
    @NotBlank
      String name,
    @NotBlank
     String loginId,
    @NotBlank
      String password
) { }

