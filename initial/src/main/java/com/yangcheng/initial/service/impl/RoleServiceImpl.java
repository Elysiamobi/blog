package com.yangcheng.initial.service.impl;

import com.yangcheng.initial.entity.Role;
import com.yangcheng.initial.Repository.RoleRepository;
import com.yangcheng.initial.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role assignRoleToUser(String roleName) {
        // 如果角色不存在则创建
        Optional<Role> role = getRoleByName(roleName);
        if (role.isEmpty()) {
            // 如果角色不存在，可以选择创建角色，或者抛出异常
            throw new RuntimeException("Role not found: " + roleName);
        }
        return role.get();
    }
}