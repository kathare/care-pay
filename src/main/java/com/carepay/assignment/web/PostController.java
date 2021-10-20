package com.carepay.assignment.web;

import javax.validation.Valid;

import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.helper.CustomResponse;
import com.carepay.assignment.helper.ResponseUtil;
import com.carepay.assignment.service.PostService;
import com.carepay.assignment.service.PostServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author  Kelvin @2021
 *  ResponseEntity has been used to show that it can be useful when there is need to fully configure HTTP responses.
 */

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getPosts(Pageable pageable) {
        CustomResponse<?> customResponse  = postService.getPosts(pageable);
        return ResponseUtil.getResponse(customResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
        CustomResponse<?> customResponse  =  postService.createPost(createPostRequest);
        return ResponseUtil.getResponse(customResponse);
    }

    @GetMapping("{id}")
    ResponseEntity<?> getPostDetails(@PathVariable("id") final Long id) {
        CustomResponse<?> customResponse  = postService.getPostDetails(id);
        return ResponseUtil.getResponse(customResponse);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> deletePost(@PathVariable("id") final Long id) {
        CustomResponse<?> customResponse  =  postService.deletePost(id);
        return ResponseUtil.getResponse(customResponse);
    }
}
