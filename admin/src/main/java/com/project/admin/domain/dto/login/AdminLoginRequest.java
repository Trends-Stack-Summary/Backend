package com.project.admin.domain.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public record AdminLoginRequest (

    @NotBlank
     String loginId,
    @NotBlank
     String password
){}
