package com.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
