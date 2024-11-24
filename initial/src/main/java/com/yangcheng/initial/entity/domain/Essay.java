package com.yangcheng.initial.entity.domain;

import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name="essay")
@Data
public class Essay implements Serializable {
    private String id;
    private String userId;
    private String title;
    private String content;
    private String tags;
    private Integer favourNum;
    private Integer commentNum;
    private Integer top;
    private String CreateTime;
    private Integer isDelete;
    @Serial
    private static final long serialVersionUID=1L;
}
