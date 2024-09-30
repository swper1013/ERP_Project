package com.example.demo.repository;

import com.example.demo.entity.BimgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BimgRepository  extends JpaRepository<BimgEntity,Long> {

    @Query("select b from BimgEntity b where b.materialEntity.num = :num")
    BimgEntity findpk(Long num);
    BimgEntity findByMaterialEntityNum(Long num);
}
