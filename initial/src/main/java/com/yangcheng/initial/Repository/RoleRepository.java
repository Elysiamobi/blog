package com.yangcheng.initial.Repository;

import com.yangcheng.initial.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // 根据角色名称查找角色
    Role findByRoleName(String roleName);

}
