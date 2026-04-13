package com.project.admin.domain.service;

import com.project.admin.dto.AdminSigninResponse;
import com.project.admin.domain.entity.Admin;
import com.project.admin.domain.repository.AdminRepository;
import com.project.admin.exception.admin.AdminErrorCode;
import com.project.admin.exception.admin.AdminException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public void createAdmin(String name, String loginId, String password) {


        if (adminRepository.existsByLoginId(loginId)) {

            throw new AdminException(AdminErrorCode.DUPLICATE_LOGIN_ID);
        }


        String encodedPassword = passwordEncoder.encode(password);


        Admin admin = Admin.create(name, loginId, encodedPassword);

        adminRepository.save(admin);


    }


    public AdminSigninResponse adminSignin(String loginId, String password) {


        try {


            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginId, password));

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException e) {
            throw new AdminException(AdminErrorCode.LOGIN_FAILED);
        }
        Admin admin = adminRepository.findByLoginId(loginId).orElseThrow(() -> new AdminException(AdminErrorCode.LOGIN_FAILED));

        return new AdminSigninResponse(admin.getId(), admin.getName());
    }

}
