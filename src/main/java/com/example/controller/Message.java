package com.example.controller;

public class Message {
    private final String role;
    private final String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getters
    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}