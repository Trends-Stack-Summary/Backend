package com.project.admin.domain.dto.login;

import lombok.Builder;


@Builder
public record AdminLoginCommand (

     String loginId,
     String password
) {
    public static AdminLoginCommand from(AdminLoginRequest request) {


        return AdminLoginCommand.builder()
                .loginId(request.loginId())
                .password(request.password())
                .build();

    }
}



