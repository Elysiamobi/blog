package com.yangcheng.initial.entity.dto.user;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String id;
    private String userPassword;
    private String email;
    private String phone;
    private String hobby;
}
