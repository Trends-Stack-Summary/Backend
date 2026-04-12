package com.project.admin.domain.dto.login;

import com.project.admin.domain.entity.Admin;

public record LoginResponse(
        Long adminId,
        String name
) {
}
