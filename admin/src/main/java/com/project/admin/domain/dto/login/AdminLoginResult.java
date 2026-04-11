package com.project.admin.domain.dto.login;

import com.project.admin.domain.entity.Admin;

public record AdminLoginResult(
        Long id,
        String name
) {
    public static AdminLoginResult from(Admin admin) {

        return new AdminLoginResult(admin.getId() , admin.getName());
    }
}
