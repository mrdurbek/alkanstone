package com.alkan.AlkanStone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkan.AlkanStone.entity.Catalog;


public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
	
}
