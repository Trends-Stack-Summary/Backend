package com.project.admin.domain.service;

import com.project.admin.domain.controller.dto.AdminSigninRequest;
import com.project.admin.domain.controller.dto.AdminSigninResponse;
import com.project.admin.domain.controller.dto.AdminSignupRequest;
import com.project.admin.domain.repository.AdminRepository;
import com.project.admin.domain.exception.AdminException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    AdminSignupRequest request;

    @BeforeEach
    void setUp() {

        request = new AdminSignupRequest("name", "admin123", "password");
        adminService.createAdmin(request.name(), request.loginId(), request.password());

    }

    @Test
    @DisplayName("관리자 생성 성공")
    void createAdmin() {


        assertThat(adminRepository.existsByLoginId("admin123")).isTrue();


    }

    @Test
    @DisplayName("관리자 생성 실패")
    void createAdmin_fail() throws IllegalStateException {


        assertThat(adminRepository.existsByLoginId("admin123")).isTrue();

        assertThatThrownBy(() -> adminService.createAdmin(request.name(), request.loginId(), request.password())).isInstanceOf(AdminException.class)
                .hasMessage("이미 존재하는 아이디 입니다");


    }


}