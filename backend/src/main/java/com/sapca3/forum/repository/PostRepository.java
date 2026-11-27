package com.sapca3.forum.repository;

import com.sapca3.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByThread_IdOrderByCreatedAtDesc(Long threadId);
}