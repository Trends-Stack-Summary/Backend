package com.project.admin.domain.service;

import com.project.admin.dto.AdminSigninRequest;
import com.project.admin.dto.AdminSigninResponse;
import com.project.admin.dto.AdminSignupRequest;
import com.project.admin.domain.repository.AdminRepository;
import com.project.admin.exception.admin.AdminException;
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
    void adminSignin() {


        AdminSigninResponse response = adminService.adminSignin(request.loginId(), request.password());

        assertThat(response.name()).isEqualTo(request.name());


    }

    @Test
    @DisplayName("로그인 실패")
    void adminSignin_fail() {


        AdminSigninRequest login = new AdminSigninRequest("admin", "password");

        assertThatThrownBy(() -> adminService.adminSignin(login.loginId(), login.password())).isInstanceOf(AdminException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다");


    }

    @Test
    @DisplayName("로그인 실패")
    void adminSignin_fail2() {


        AdminSigninRequest login = new AdminSigninRequest("admin123", "123");

        assertThatThrownBy(() -> adminService.adminSignin(login.loginId(), login.password())).isInstanceOf(AdminException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다");


    }
}