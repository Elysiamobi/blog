package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.Role;
import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByName(String roleName);
    Role assignRoleToUser(String roleName);
}
