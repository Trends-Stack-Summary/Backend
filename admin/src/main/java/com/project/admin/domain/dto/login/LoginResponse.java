package com.project.admin.domain.dto.login;

public record LoginResponse (
      Long adminId,
     String name
) {

 public    static  LoginResponse from(AdminLoginResult admin ) {
        return new LoginResponse(admin.id(), admin.name());
    }
}
