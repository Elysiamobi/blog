package com.yangcheng.initial.entity.dto.user;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String userAccount;
    private String userPassword;
}