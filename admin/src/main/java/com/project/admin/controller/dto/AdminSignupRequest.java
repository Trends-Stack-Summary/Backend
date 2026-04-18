package com.project.admin.controller.dto;

import jakarta.validation.constraints.NotBlank;


public record AdminSignupRequest(
        @NotBlank(message = "이름을 입력 해주세요.")
        String name,
        @NotBlank(message = "아이디를 입력 해주세요.")
        String loginId,
        @NotBlank(message = "비밀번호를 입력 해주세요.")
        String password
) {
}

