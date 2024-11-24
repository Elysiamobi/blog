package com.yangcheng.initial.entity.dto.comment;

import lombok.Data;

@Data
public class CommentQueryRequest {
    private String userId;
    private String essayId;
    private String replyTo;
}
