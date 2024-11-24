package com.yangcheng.initial.entity.dto.essay;

import com.yangcheng.initial.common.PageQueryRequest;
import lombok.Data;

@Data
public class EssayQueryRequest extends PageQueryRequest {
    private String userId;
    private String title;
private String content;
private String tags;

}
