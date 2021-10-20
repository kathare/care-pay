package com.carepay.assignment.repository;

import com.carepay.assignment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long id, Pageable pageable);
    Comment findByIdAndPostId(Long id, Long postId);
    List<Comment> findByPostId(Long id);
}
