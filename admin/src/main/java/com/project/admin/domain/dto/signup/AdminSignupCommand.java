package com.project.admin.domain.dto.signup;
import lombok.Builder;

@Builder
public record AdminSignupCommand (
      String name,
     String loginId,
      String password
)
    {
    public  static AdminSignupCommand from(AdminSignupRequest request) {


        return  AdminSignupCommand.builder()
                .name(request.name())
                .loginId(request.loginId())
                .password(request.password())
                .build();

    }

}
