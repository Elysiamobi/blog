package com.yangcheng.initial.service;

import com.yangcheng.initial.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);

    default User createUser() {
        return createUser(null);
    }

    User createUser(User user);
    boolean checkIfUserExists(String username);
}
