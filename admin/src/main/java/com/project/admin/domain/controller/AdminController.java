package com.project.admin.domain.controller;

import com.project.admin.domain.dto.login.LoginResponse;
import com.project.admin.domain.dto.login.AdminLoginRequest;
import com.project.admin.domain.dto.signup.AdminSignupRequest;
import com.project.admin.domain.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody AdminLoginRequest loginRequest, HttpServletRequest request) {

        LoginResponse loginAdmin = adminService.loginAdmin(loginRequest.loginId(), loginRequest.password());

        HttpSession session = request.getSession();
        session.setAttribute("admin", loginAdmin.adminId());


        return ResponseEntity.ok(loginAdmin);

    }
}
