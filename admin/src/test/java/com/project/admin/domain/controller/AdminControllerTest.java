package com.project.admin.domain.controller;

import com.project.admin.domain.dto.login.AdminLoginRequest;
import com.project.admin.domain.dto.login.LoginResponse;
import com.project.admin.domain.dto.signup.AdminSignupRequest;
import com.project.admin.domain.service.AdminService;
import com.project.admin.exception.AdminException;
import com.project.admin.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AdminService adminService;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    @DisplayName("로그인 성공")
    void login() throws  Exception {

        AdminLoginRequest request = new AdminLoginRequest("admin123", "password");
        LoginResponse result = new LoginResponse(1L, "name");

        given(adminService.loginAdmin(any(),any())).willReturn(result);

        mockMvc.perform(post("/admin/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"));

    }

    @Test
    @DisplayName("로그인 실패")
    void login_fail() throws  Exception {

        AdminLoginRequest request = new AdminLoginRequest("admin123", "password");

        given(adminService.loginAdmin(any(),any())).willThrow(new AdminException(ErrorCode.LOGIN_FAILED));

        mockMvc.perform(post("/admin/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @DisplayName("회원가입 성공")
    void  signup() throws  Exception {

        AdminSignupRequest request=  new AdminSignupRequest("name","admin123","password");

        mockMvc.perform(post("/admin/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("회원가입 실패")
    void  signup_fail() throws  Exception {

        AdminSignupRequest request=  new AdminSignupRequest("name","admin123","password");

        doThrow(new AdminException(ErrorCode.DUPLICATE_LOGIN_ID))
                .when(adminService).createAdmin(any(),any(),any());

        mockMvc.perform(post("/admin/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }

}