package com.carepay.assignment.domain;

import lombok.Data;

import java.util.List;

@Data
public class PostDetails extends PostInfo {
    private String content;
    private String user;
}
