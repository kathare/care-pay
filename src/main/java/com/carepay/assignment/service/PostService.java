package com.carepay.assignment.service;

import javax.validation.Valid;

import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.domain.PostDetails;
import com.carepay.assignment.domain.PostInfo;
import com.carepay.assignment.helper.CustomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    CustomResponse<?> createPost(@Valid CreatePostRequest createPostRequest);

    CustomResponse<?>  getPosts(final Pageable pageable);

    CustomResponse<?>  getPostDetails(Long id);

    CustomResponse<?>  deletePost(Long id);
}
