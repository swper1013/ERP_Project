package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EmployeDTO {
    private Long eno;               //번호

    private String job;             //직무
    private String rank;            //직급
    private LocalDate join_date;    //입사일자
    private int sal;                //월급

    private Long mno;               //FK
}
