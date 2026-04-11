package com.project.admin.domain.controller;

import com.project.admin.domain.dto.login.AdminLoginResult;
import com.project.admin.domain.dto.login.LoginResponse;
import com.project.admin.domain.dto.login.AdminLoginCommand;
import com.project.admin.domain.dto.login.AdminLoginRequest;
import com.project.admin.domain.dto.signup.AdminSignupCommand;
import com.project.admin.domain.dto.signup.AdminSignupRequest;
import com.project.admin.domain.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/admin/signup")
    private ResponseEntity<String> signup(@Valid @RequestBody AdminSignupRequest request) {

        adminService.createAdmin(AdminSignupCommand.from(request));

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 완료");

    }


    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody AdminLoginRequest loginRequest, HttpServletRequest request) {

        AdminLoginResult loginAdmin = adminService.loginAdmin(AdminLoginCommand.from(loginRequest));

        HttpSession session = request.getSession();
        session.setAttribute("admin", loginAdmin.id());


        log.info("session={}", session);

        return ResponseEntity.ok(LoginResponse.from(loginAdmin));

    }
}
