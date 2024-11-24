package com.yangcheng.initial.entity.domain;

import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name="follow")
@Data
public class Follow implements Serializable {
    private String id;
    private String FollowId;
    private String followerId;
    @Serial
    private static final long serialVersionUID=1L;
}
