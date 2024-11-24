package com.yangcheng.initial.entity.dto.essay;

import lombok.Data;

@Data
public class EssayUpdateRequest {
    private String id;
    private String userId;
    private String title;
    private String content;
    private String tags;
    private Integer favourNum;
    private Integer commentNum;
    private Integer forwardNum;
    private Integer top;


}
