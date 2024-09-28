package com.example.demo.service;

import com.example.demo.dto.MaterialDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponesDTO;

import java.util.List;

public interface MaterialService {

    public void register(MaterialDTO materialDTO);
    public List<MaterialDTO> selectAll();
    public MaterialDTO read(Long num);
    public void update(MaterialDTO materialDTO);
    public Long delete(Long num);
    public PageResponesDTO<MaterialDTO> list(PageRequestDTO pageRequestDTO);


}