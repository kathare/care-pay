package com.carepay.assignment.web;

import com.carepay.assignment.domain.*;
import com.carepay.assignment.helper.CustomResponse;
import com.carepay.assignment.helper.ResponseUtil;
import com.carepay.assignment.service.CommentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author  Kelvin @2021
 *  ResponseEntity has been used to show that it can be useful when there is need to fully configure HTTP responses.
 */

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{id}/comments")
    public  ResponseEntity<?>  getComments(Pageable pageable,@PathVariable("id") final Long postId ) {
        CustomResponse<?> customResponse  = commentService.getComments(pageable, postId);
        return ResponseUtil.getResponse(customResponse);
    }

    @PostMapping ("{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
   public  ResponseEntity<?>  createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest, @PathVariable("id") final Long postId ) {
                CustomResponse<?> customResponse  = commentService.createComment(createCommentRequest, postId);
                return ResponseUtil.getResponse(customResponse);
    }

    @GetMapping("{postId}/comments/{id}")
    public ResponseEntity<?> getCommentDetails(@PathVariable("id") final Long id, @PathVariable("postId") final Long postId) {
        CustomResponse<?> customResponse = commentService.getCommentDetails(id,postId);
        return ResponseUtil.getResponse(customResponse);

    }

    @DeleteMapping("{postId}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?>  deleteComment(@PathVariable("id") final Long id, @PathVariable("postId") final Long postId) {
        CustomResponse<?> customResponse  =   commentService.deleteComment(id,postId);
        return ResponseUtil.getResponse(customResponse);
    }
}
