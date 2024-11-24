package com.yangcheng.initial.entity.dto.comment;

import lombok.Data;

@Data
public class CommentAddRequest {
    private String userId;
    private String content;
    private String replyTo;

}
