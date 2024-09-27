package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class UsersEntity_임시 {
    @Id
    @Column(length = 15, nullable = false, unique = true)
    private String id; // ID

    @Column(length = 15, nullable = false, unique = true)
    private String pass; // 비밀번호

    @Column(length = 10, nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private int age; //나이

    @Column(nullable = false)
    private String gender; //성별

    @Column(length = 20, nullable = false)
    private String email; //이메일

    @Column(length = 10, nullable = false)
    private String phone; //연락처

    @Column(length = 20, nullable = false,unique = true)
    private String B2BName; //상호명

    @Column(length = 50, nullable = false,unique = true)
    private String B2BAddr; //회사주소

    @Column(length = 10, nullable = false)
    private String B2BExpont; //대표자 성함

    @Column(length = 20, nullable = false)
    private String B2BEmail; //대표 이메일

    @Column(length = 10, nullable = false,unique = true)
    private String B2BContact; //회사 연락처

    @Column(length = 20, nullable = false, unique = true)
    private String B2BNumber; //사업자등록번호

}
