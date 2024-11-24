package com.yangcheng.initial.entity.domain;

import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name="user")
@Data
public class User implements Serializable {
    private String id;
    private String user_account;
    private String user_password;
    private String user_role;
    private String email;
    private String phone;
    private String hobby;
    private Integer is_delete;
    @Serial
    private static final long serialVersionUID=1L;
}
