package com.ilsy.Security.Repo;

import com.ilsy.Security.Models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
