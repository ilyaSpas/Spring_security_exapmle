package com.ilsy.Security.Repo;

import com.ilsy.Security.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
