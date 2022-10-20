package com.alkan.AlkanStone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkan.AlkanStone.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Integer> {
	
}
