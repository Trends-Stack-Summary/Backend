package com.project.admin.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String loginId;
@Column(nullable = false)
    private  String password;




    @Builder
      private  Admin(String name, String loginId, String password) {

        this.name= name;
        this.loginId = loginId;
        this.password = password;


    }






}
