package com.yangcheng.initial.Controller;

import com.yangcheng.initial.entity.Role;
import com.yangcheng.initial.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 根据角色名称获取角色信息
    @GetMapping("/{roleName}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String roleName) {
        Optional<Role> role = roleService.getRoleByName(roleName);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 给用户分配角色
    @PostMapping("/assign")
    public ResponseEntity<Role> assignRoleToUser(@RequestParam String roleName) {
        Role role = roleService.assignRoleToUser(roleName);
        return ResponseEntity.ok(role);
    }
}
