package com.yangcheng.initial.entity.vo;

import com.yangcheng.initial.entity.domain.User;
import lombok.Data;

@Data
public class CommentVo {
private String id;
private User user;
private String content;
private String replyTo;
private String createTime;
}
