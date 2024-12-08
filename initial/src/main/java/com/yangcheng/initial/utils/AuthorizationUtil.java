package com.yangcheng.initial.utils;

import com.yangcheng.initial.entity.User;

public class AuthorizationUtil {

    public static boolean canModify(User loggedInUser, Integer ownerId) {
        // Admins can modify anything
        if (loggedInUser != null && loggedInUser.isAdmin()) {
            return true;
        }
        // Users can only modify their own data
        return loggedInUser != null && loggedInUser.getUserId().equals(ownerId);
    }
}
