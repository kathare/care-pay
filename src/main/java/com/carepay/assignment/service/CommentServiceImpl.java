package com.carepay.assignment.service;

import com.carepay.assignment.domain.*;
import com.carepay.assignment.helper.CustomMessage;
import com.carepay.assignment.helper.CustomResponse;
import com.carepay.assignment.repository.CommentRepository;
import com.carepay.assignment.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

/**
 *
 * @author  Kelvin @2021
 *  Added CustomResponse to be able to format response nicely. It is important epecially when intergrating.
 */

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final  PostRepository postRepository;

    public CommentServiceImpl (CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    // helper function for getting post
    public Post getPost(Long id){
        if (id == null || id.toString().isEmpty())
            return null;

        Post post = postRepository.findById(id).orElse(null);
        return post;
    }

    @Override
    public CustomResponse<?> createComment(@Valid CreateCommentRequest createCommentRequest, Long postId) {
        CustomResponse customResponse =  new CustomResponse();
        CustomMessage customMessage = new CustomMessage();
        Comment newComment = new Comment();
        CommentDetails commentDetails = new CommentDetails();
        if (getPost(postId) == null){
            customMessage.setMessage("Comment can only be added to an existing post");
            return new CustomResponse<>(customMessage);
        }
        newComment.setPost(getPost(postId));
        newComment.setComment(createCommentRequest.getComment());
        newComment.setUserName("Test User2");
        newComment.setCreatedOn(new Date());
        Comment savedComment = commentRepository.save(newComment);

        commentDetails.setId(savedComment.getId());
        commentDetails.setComment(savedComment.getComment());
        commentDetails.setUser(savedComment.getUserName());

        customResponse.setMessage("Success");
        customResponse.setData(commentDetails);
        return customResponse;
    }

    @Override
    public  CustomResponse<?>  getComments(final Pageable pageable, Long postId) {
        CustomResponse customResponse = new CustomResponse();
        CustomMessage customMessage = new CustomMessage();
        Page<Comment> findAllComments = commentRepository.findByPostId(postId, pageable);
        if (findAllComments.getContent().isEmpty()){
            customMessage.setMessage("No data found");
            return new CustomResponse<>(customMessage);
        }

        Page<CommentDetails> pagedData = findAllComments.map(pg -> {
            CommentDetails commentDetails = new CommentDetails();
            commentDetails.setId(pg.getId());
            commentDetails.setComment(pg.getComment());
            commentDetails.setUser(pg.getUserName());
            return commentDetails;
        });
        customResponse.setMessage("Success");
        customResponse.setData(pagedData);
        return customResponse;
    }

    @Override
    public CustomResponse<?>  getCommentDetails(Long id, Long postId) {
        CustomResponse customResponse = new CustomResponse();
        CustomMessage customMessage = new CustomMessage();
        CommentDetails commentDetails = new CommentDetails();
        if (getPost(postId) == null){
            customMessage.setMessage("Post does not exist");
            return new CustomResponse<>(customMessage);
        }
        Comment comment = commentRepository.findByIdAndPostId(id, postId);
        if (comment == null){
            customMessage.setMessage("We cannot retrieve your comment at the moment");
            return new CustomResponse<>(customMessage);
        }

        commentDetails.setId(comment.getId());
        commentDetails.setComment(comment.getComment());
        commentDetails.setUser(comment.getUserName());

        customResponse.setMessage("Success");
        customResponse.setData(commentDetails);
        return  customResponse;

    }

    @Override
    public CustomResponse<?> deleteComment(Long id, Long postId) {
        CustomMessage customMessage = new CustomMessage();
        Comment comment = commentRepository.findById(id).orElse(null);
        try {
            if (getPost(postId) == null){
                customMessage.setMessage("Post is not available");
            }else if(comment == null) {
                customMessage.setMessage("Comment is currently unavailable");
            } else {
                commentRepository.deleteById(id);
                customMessage.setMessage("Success");
            }
        } catch (Exception e){
            customMessage.setMessage(e.getCause().toString());
        }

        return new CustomResponse<>(customMessage);
    }
}
