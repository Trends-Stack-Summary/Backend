package com.project.admin.domain.controller;

import com.project.admin.dto.AdminSigninResponse;
import com.project.admin.dto.AdminSigninRequest;
import com.project.admin.dto.AdminSignupRequest;
import com.project.admin.domain.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/admin/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody AdminSignupRequest request) {

        adminService.createAdmin(request.name(), request.loginId(), request.password());

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");

    }


    @PostMapping("/admin/signin")
    public ResponseEntity<AdminSigninResponse> signin(@Valid @RequestBody AdminSigninRequest loginRequest) {

        AdminSigninResponse loginAdmin = adminService.adminSignin(loginRequest.loginId(), loginRequest.password());


        return ResponseEntity.ok(loginAdmin);

    }
}
