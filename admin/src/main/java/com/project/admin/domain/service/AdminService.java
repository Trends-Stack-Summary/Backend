package com.project.admin.domain.service;

import com.project.admin.domain.dto.login.LoginResponse;
import com.project.admin.domain.entity.Admin;
import com.project.admin.domain.repository.AdminRepository;
import com.project.admin.exception.AdminException;
import com.project.admin.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void createAdmin(String name, String loginId, String password) {


        if (adminRepository.existsByLoginId(loginId)) {

            throw new AdminException(ErrorCode.DUPLICATE_LOGIN_ID);
        }


        String encodedPassword = passwordEncoder.encode(password);


        Admin admin = Admin.create(name, loginId, encodedPassword);

        adminRepository.save(admin);


    }


    public LoginResponse loginAdmin(String loginId, String password) {


        Admin admin = adminRepository.findByLoginId(loginId).orElseThrow(() -> new AdminException(ErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(password, admin.getPassword())) {

            throw new AdminException(ErrorCode.LOGIN_FAILED);


        }

        return new  LoginResponse(admin.getId(), admin.getName());
    }

}
