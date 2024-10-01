package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.MaterialDTO;
import com.example.demo.dto.PageRequestDTO;
import com.example.demo.dto.PageResponesDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.MaterialEntity;
import com.example.demo.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class MaterialServiceImpl implements MaterialService {



    @Value("${materialImgLocation}")

    private String MaterialImgLocation;

    private final MaterialRepository materialRepository;
    private ModelMapper mapper = new ModelMapper();
    private final BimgSerivce bimgSerivce;

    @Override
    public boolean register(MaterialDTO materialDTO, MultipartFile multipartFile) {




        log.info("dto들어옴"+materialDTO);

        MaterialEntity materialEntity =  materialRepository.findByMatCode(materialDTO.getMatCode());

        if(materialEntity == null){
             materialEntity = mapper.map(materialDTO, MaterialEntity.class);
            materialRepository.save(materialEntity);
            bimgSerivce.Bimgregister(materialEntity, multipartFile, MaterialImgLocation);
            log.info("여기는 레지 진행완");
            return true;
        }else {
            log.info("저장 불가중복");
            return false;
        }



    }





    @Override
    public List<MaterialDTO> selectAll() {
        List<MaterialEntity> materialEntityList = materialRepository.findAll();

        List<MaterialDTO> materialDTOList=
                materialEntityList.stream().map(abc->mapper.map(abc, MaterialDTO.class)).collect(Collectors.toList());
        return materialDTOList;
    }

    @Override
    public MaterialDTO read(Long num) {
        MaterialEntity materialEntity =
                materialRepository.findById(num).orElseThrow(EntityNotFoundException::new);
        MaterialDTO materialDTO = mapper.map(materialEntity, MaterialDTO.class);
        return materialDTO;
    }

    @Override
    public void update(MaterialDTO materialDTO) {

        if (materialDTO.getNum() == null) {
            throw new IllegalArgumentException("아 혹시 이부분인가요?");
        }
        MaterialEntity materialEntity = materialRepository.findById(materialDTO.getNum())
                .orElseThrow(EntityNotFoundException::new);


        materialEntity.setMatName(materialDTO.getMatName());
        materialEntity.setMatCode(materialDTO.getMatCode());
        materialEntity.setMatAmount(materialDTO.getMatAmount());
        materialEntity.setMatPrice(materialDTO.getMatPrice());
        materialEntity.setMatBuyPlace(materialDTO.getMatBuyPlace());
        materialEntity.setMatBuyNum(materialDTO.getMatBuyNum());
        materialEntity.setMatText(materialDTO.getMatText());
        materialEntity.setMatBuyDate(materialDTO.getMatBuyDate());



    }

    @Override
    public Long delete(Long num) {
        MaterialEntity materialEntity =  materialRepository.findById(num).get();
        if (materialEntity != null) {
            materialRepository.deleteById(num);
            log.info("혹시 서비스까진 오셨나요");
            return materialEntity.getNum();
        } else {
            return null;
        }

    }

    @Override
    public PageResponesDTO<MaterialDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        log.info("서비스에서 변환된 :  " + types);

        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("num");
        log.info(pageable.getSort());

        Page<MaterialDTO> materialDTOPage = materialRepository.searchAll(types, keyword, pageable);

        log.info("레포지토리에서 값은 서비스로 받았니?");


        return PageResponesDTO.<MaterialDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(materialDTOPage.getContent())
                .total(  (int)  materialDTOPage.getTotalElements())
                .build();
    }
}
