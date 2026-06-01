package com.project.admin.controller.spec;

import com.project.admin.controller.dto.request.admin.AdminSigninRequest;
import com.project.admin.controller.dto.request.admin.AdminSignupRequest;
import com.project.admin.controller.dto.response.MeResponse;
import com.project.admin.security.handler.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "signin/signup")
public interface AdminApi {

    @Operation(summary = "관리자 회원가입")
    @ApiResponse(responseCode = "201")
    ResponseEntity<Void> signup(@Valid @RequestBody AdminSignupRequest request);

    @Operation(summary = "관리자 로그인")
    LoginResponse signin(AdminSigninRequest request);

    @Operation(summary = "세션 확인",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인 상태"),
                    @ApiResponse(responseCode = "401", description = "비로그인 상태", content = @Content)
            })
    public ResponseEntity<MeResponse> me(Authentication authentication);


}
