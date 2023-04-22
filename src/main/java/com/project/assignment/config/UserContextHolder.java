package com.project.assignment.config;

import com.project.assignment.constant.UserEnum;

public class UserContextHolder {
    private static ThreadLocal<UserEnum> threadLocal = new ThreadLocal<>();

    public static void setBranchContext(UserEnum userEnum) {
        threadLocal.set(userEnum);
    }

    public static UserEnum getBranchContext() {
        return threadLocal.get();
    }

    public static void clearBranchContext() {
        threadLocal.remove();
    }
}
