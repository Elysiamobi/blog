package com.yangcheng.initial.entity.dto.follow;

import lombok.Data;

@Data
public class FollowUpdateRequest {
    private String id;
    private String followId;
    private String FollowerId;
    private int type;
}
