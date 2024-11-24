package com.yangcheng.initial.entity.dto.essay;

import lombok.Data;

@Data
public class EssayAddRequest {
    private String userId;
    private String title;
    private String content;
    private String tags;
    private Integer top;
}
