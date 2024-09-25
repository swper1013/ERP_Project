package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BoardDTO {

    private Long bno;           //글번호

    @NotNull
    @Size(min = 2, max = 50)
    private String title;       //제목

    @NotEmpty
    private String content;     //내용

    private String writer;      //작성자
    private LocalDate regiDate; //작성일시
    private LocalDate modDate;  //수정일시

}
