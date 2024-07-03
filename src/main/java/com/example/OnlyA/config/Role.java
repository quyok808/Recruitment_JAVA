package com.example.OnlyA.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    ADMIN("1"), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống.
    PERSONAL("2"), // Vai trò người dùng bình thường, có quyền hạn giới hạn.
    BUSINESS("3");
    public final String value; // Biến này lưu giá trị số tương ứng với mỗi vai trò.

    public String getValue() {
        return value;
    }
}
