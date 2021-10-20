package com.carepay.assignment.domain;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
}
