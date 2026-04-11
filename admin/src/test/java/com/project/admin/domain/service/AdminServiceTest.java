package com.project.admin.domain.service;

import com.project.admin.domain.dto.login.AdminLoginCommand;
import com.project.admin.domain.dto.login.AdminLoginResult;
import com.project.admin.domain.dto.signup.AdminSignupCommand;
import com.project.admin.domain.repository.AdminRepository;
import com.project.admin.domain.service.AdminService;
import com.project.admin.exception.AdminException;
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

    @Test
    @DisplayName("관리자 생성 성공")
    void createAdmin() {


        AdminSignupCommand command = new AdminSignupCommand("name","admin123","password");

        adminService.createAdmin(command);

        assertThat(adminRepository.existsByLoginId("admin123")).isTrue();



    }

    @Test
    @DisplayName("관리자 생성 실패")
    void createAdmin_fail() throws IllegalStateException {


        AdminSignupCommand command = new AdminSignupCommand("name","admin123","password");

        adminService.createAdmin(command);

        assertThat(adminRepository.existsByLoginId("admin123")).isTrue();

        assertThatThrownBy(() -> adminService.createAdmin(command)).isInstanceOf(AdminException.class)
                        .hasMessage("이미 존재하는 아이디 입니다");



    }

    @Test
    @DisplayName("로그인 성공")
    void loginAdmin() {

        AdminSignupCommand command = new AdminSignupCommand("name","admin123","password");

        adminService.createAdmin(command);

        AdminLoginCommand login = new AdminLoginCommand("admin123","password");

        AdminLoginResult adminLoginResult = adminService.loginAdmin(login);


        assertThat(adminLoginResult.name()).isEqualTo(command.name());


    }
    @Test
    @DisplayName("로그인 실패")
    void loginAdmin_fail() {

        AdminSignupCommand command = new AdminSignupCommand("name","admin123","password");

        adminService.createAdmin(command);


        AdminLoginCommand login = new AdminLoginCommand("admin","password");

        assertThatThrownBy(()-> adminService.loginAdmin(login)).isInstanceOf(AdminException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다");


    }

    @Test
    @DisplayName("로그인 실패")
    void loginAdmin_fail2() {

        AdminSignupCommand command = new AdminSignupCommand("name","admin123","password");

        adminService.createAdmin(command);


        AdminLoginCommand login = new AdminLoginCommand("admin123","passwor");

        assertThatThrownBy(()-> adminService.loginAdmin(login)).isInstanceOf(AdminException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다");


    }
}