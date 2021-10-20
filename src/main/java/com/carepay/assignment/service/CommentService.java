package com.carepay.assignment.service;

import com.carepay.assignment.domain.*;
import com.carepay.assignment.helper.CustomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface CommentService {
    CustomResponse<?> createComment(@Valid CreateCommentRequest createCommentRequest, Long postId);

    CustomResponse<?>  getComments(final Pageable pageable, Long postId);

    CustomResponse<?> getCommentDetails(Long id, Long postId);

    CustomResponse<?> deleteComment(Long id,  Long postId);
}
