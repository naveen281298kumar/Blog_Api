package com.blog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    
    private int id;
    @NotBlank
    private String content;

    private int postId;

    private String postTitle;
}
