package com.yangcheng.initial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleName;  // 角色名称（比如 "USER", "ADMIN"）

    @OneToMany(mappedBy = "role")
    private List<User> users;  // 角色对应的用户
}
