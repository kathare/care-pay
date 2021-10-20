package com.carepay.assignment.domain;

import lombok.Data;

import java.util.List;

/**
 *
 * @author  Kelvin @2021
 *  This class has list data for comments. This is needed to help return a detailed list of post comments.
 *  However, it should be noted that this can be a challenege where we have a lot of comments in a single post.
 *  Pagination is the way to go for nested data.
 */


@Data
public class PostData extends PostInfo {
    private String content;
    private String user;
    private List<CommentDetails> comments;
}
