package com.yangcheng.initial.entity.domain;

import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name= "Comment")
@Data
public class Comment implements Serializable {
private String id;
private String essayId;
private String userId;
private String content;
private String reply_to;
private String create_time;
private Integer is_delete;
@Serial
private static final long serialVersionUID=1L;

}
