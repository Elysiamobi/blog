package com.yangcheng.initial.common;

import lombok.Data;

@Data
public class InteractionUpdateRequest {


    private String id;


    private String essayId;


    private String userId;

    /**
     *  互动类型，操作0，取消操作1
     */
    private int type;
}
