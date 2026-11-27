package com.sapca3.forum.repository;

import com.sapca3.forum.model.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadRepository extends JpaRepository<ForumThread, Long>{
    List<ForumThread> findByCategory_IdOrderByCreatedAtDesc(Long categoryId);

    List<ForumThread> findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCaseOrderByCreatedAtDesc(String title,
                                                                                                    String body);
}