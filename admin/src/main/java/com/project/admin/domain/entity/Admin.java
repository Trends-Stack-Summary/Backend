package com.project.admin.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Admin {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String loginId;
@Column(nullable = false)
    private  String password;





      public static  Admin create(String name, String loginId, String password) {

       return  Admin.builder()
               .name(name)
               .loginId(loginId)
               .password(password)
               .build();


    }






}
