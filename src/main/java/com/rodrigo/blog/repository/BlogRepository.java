package com.rodrigo.blog.repository;

import com.rodrigo.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Post, Long> {
}
