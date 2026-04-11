package com.project.admin.domain.service;

import com.project.admin.domain.entity.Admin;
import com.project.admin.domain.dto.login.AdminLoginCommand;
import com.project.admin.domain.dto.login.AdminLoginResult;
import com.project.admin.domain.dto.signup.AdminSignupCommand;
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
    public void createAdmin(AdminSignupCommand command) {


        if(adminRepository.existsByLoginId(command.loginId())) {

            throw  new AdminException(ErrorCode.DUPLICATE_LOGIN_ID);
        }


        String password = passwordEncoder.encode(command.password());


        Admin admin = Admin.create(command.name(), command.loginId(),password);

        adminRepository.save(admin);


    }


    public AdminLoginResult loginAdmin(AdminLoginCommand command) {


        Admin admin = adminRepository.findByLoginId(command.loginId()).orElseThrow(() -> new AdminException(ErrorCode.LOGIN_FAILED));

        if(!passwordEncoder.matches(command.password(), admin.getPassword())) {

            throw new AdminException(ErrorCode.LOGIN_FAILED);



        }

        return AdminLoginResult.from(admin);
    }

}
