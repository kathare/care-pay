package com.carepay.assignment.service;

import javax.validation.Valid;

import com.carepay.assignment.domain.*;
import com.carepay.assignment.helper.CustomMessage;
import com.carepay.assignment.helper.CustomResponse;
import com.carepay.assignment.repository.CommentRepository;
import com.carepay.assignment.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author  Kelvin @2021
 *  Added CustomResponse to be able to format response nicely. It is important epecially when intergrating.
 */


@Service
public class PostServiceImpl implements PostService {
    private final CommentRepository commentRepository;
    private final  PostRepository postRepository;

    public PostServiceImpl (CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @Override
    public CustomResponse<?> createPost(@Valid CreatePostRequest createPostRequest) {
        CustomResponse customResponse = new CustomResponse();
        Post newPost = new Post();
        PostDetails postDetails = new PostDetails();

        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setUserName("Test User");
        newPost.setCreatedOn(new Date());
        Post savedPost = postRepository.save(newPost);

        postDetails.setId(savedPost.getId());
        postDetails.setTitle(savedPost.getTitle());
        postDetails.setContent(savedPost.getContent());
        postDetails.setUser(savedPost.getUserName());

        customResponse.setMessage("Success");
        customResponse.setData(postDetails);

        return customResponse;
    }

    @Override
    public CustomResponse<?> getPosts(Pageable pageable) {
        CustomResponse customResponse = new CustomResponse();
        CustomMessage customMessage = new CustomMessage();
        Page<Post> findAllPosts = postRepository.findAll(pageable);
        if (findAllPosts.getContent().isEmpty()){
            customMessage.setMessage("No data found");
            return new CustomResponse<>(customMessage);
        }
        Page<PostInfo> pagedData = findAllPosts.map(pg -> {
            PostInfo postInfo = new PostInfo();
            postInfo.setId(pg.getId());
            postInfo.setTitle(pg.getTitle());
            return postInfo;
        });

        customResponse.setMessage("Success");
        customResponse.setData(pagedData);

        return customResponse;

    }

    @Override
    public CustomResponse<?> getPostDetails(Long id) {
        CustomResponse customResponse = new CustomResponse();
        CustomMessage customMessage = new CustomMessage();
        PostData postData = new PostData();
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            customMessage.setMessage("Provided id not available");
            return new CustomResponse<>(customMessage);
        }

        List<CommentDetails> commentList = commentRepository.findByPostId(id).stream().map(cm -> {
            CommentDetails commentDetails = new CommentDetails();
            commentDetails.setId(cm.getId());
            commentDetails.setComment(cm.getComment());
            commentDetails.setUser(cm.getUserName());
            return commentDetails;
        }).collect(Collectors.toList());

        postData.setId(post.getId());
        postData.setTitle(post.getTitle());
        postData.setContent(post.getContent());
        postData.setUser(post.getUserName());
        postData.setComments(commentList);

        customResponse.setMessage("Success");
        customResponse.setData(postData);

        return  customResponse;

    }

    @Override
    public CustomResponse<?> deletePost(Long id) {
        CustomMessage customMessage = new CustomMessage();
        Post post = postRepository.findById(id).orElse(null);

        try {
            if (post == null) {
                customMessage.setMessage("Provided id not available");
            }else {
                List<Comment> commentList = commentRepository.findByPostId(id);
                commentRepository.deleteAll(commentList); //delete all comments belonging to the post
                postRepository.deleteById(id);

                customMessage.setMessage("Success");
            }
        } catch (Exception e){
            customMessage.setMessage(e.getCause().toString());
        }

        return new CustomResponse<>(customMessage);
    }
}
