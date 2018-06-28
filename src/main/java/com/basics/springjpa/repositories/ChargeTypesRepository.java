package com.basics.springjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basics.springjpa.entity.ChargeTypesEntity;

@Repository
public interface ChargeTypesRepository extends JpaRepository<ChargeTypesEntity, Long> {
	ChargeTypesEntity findByChrgTypeId(Long chrgTypeId);
}
