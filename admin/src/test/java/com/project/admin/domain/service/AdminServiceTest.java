package com.project.admin.domain.service;

import com.project.admin.domain.dto.login.AdminLoginRequest;
import com.project.admin.domain.dto.login.LoginResponse;
import com.project.admin.domain.dto.signup.AdminSignupRequest;
import com.project.admin.domain.repository.AdminRepository;
import com.project.admin.exception.AdminException;
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

    @Test
    @DisplayName("로그인 성공")
    void loginAdmin() {


        LoginResponse response = adminService.loginAdmin(request.loginId(), request.password());

        assertThat(response.name()).isEqualTo(request.name());


    }

    @Test
    @DisplayName("로그인 실패")
    void loginAdmin_fail() {


        AdminLoginRequest login = new AdminLoginRequest("admin", "password");

        assertThatThrownBy(() -> adminService.loginAdmin(login.loginId(), login.password())).isInstanceOf(AdminException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다");


    }

    @Test
    @DisplayName("로그인 실패")
    void loginAdmin_fail2() {


        AdminLoginRequest login = new AdminLoginRequest("admin123", "123");

        assertThatThrownBy(() -> adminService.loginAdmin(login.loginId(), login.password())).isInstanceOf(AdminException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다");


    }
}