package com.yangcheng.initial.entity.dto.follow;

import lombok.Data;

@Data
public class FollowQueryRequest {
    private String id;
    private String followId;
    private String followerId;
}
