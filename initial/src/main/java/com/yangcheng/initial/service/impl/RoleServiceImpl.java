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
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Optional<Role> findById(Integer roleId) {
        return roleRepository.findById(roleId);
    }
}