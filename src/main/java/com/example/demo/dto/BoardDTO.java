package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private LocalDate regiDate;
    private LocalDate modDate;
}
