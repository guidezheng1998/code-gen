package com.example.utils;
import java.util.UUID;

/**
 * UuidUtil 是一个工具类，用于生成唯一的 UUID。
 */
public class UuidUtil {

    /**
     * 生成一个唯一的 UUID。
     *
     * @return 生成的 UUID 字符串
     */
    public static String generateUUID() {
        try {
            return UUID.randomUUID().toString();
        } catch (Exception e) {
            // 在极少数情况下，UUID 的生成可能会抛出异常
            System.err.println("Failed to generate UUID: " + e.getMessage());
            return null; // 或者抛出自定义异常
        }
    }

    // 可选：测试方法
    public static void main(String[] args) {
        String uuid = generateUUID();
        if (uuid != null) {
            System.out.println("Generated UUID: " + uuid);
        } else {
            System.out.println("Failed to generate UUID.");
        }
    }
}
