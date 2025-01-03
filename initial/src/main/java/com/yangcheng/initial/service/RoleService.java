package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Role;
import java.util.Optional;

public interface RoleService {
    Role findByRoleName(String roleName);
    Optional<Role> findById(Integer roleId);
}
