package com.alkan.AlkanStone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkan.AlkanStone.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
}
