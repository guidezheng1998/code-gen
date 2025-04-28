package com.example.controller;

import java.util.List;

public class CodeGenerationRequest {
    private final String model;
    private final List<Message> messages;
    private final double temperature;
    private final double top_p;
    private final boolean do_sample;
    private final int max_tokens;
    private final boolean stream;
    private final String moduleName;
    private final String functionDescription;
    private final String codeStyle;
    private final String frontEndFramework;

    public CodeGenerationRequest(String model, List<Message> messages, double temperature, double top_p, boolean do_sample, int max_tokens, boolean stream,
                                 String moduleName, String functionDescription, String codeStyle, String frontEndFramework) {
        this.model = model;
        this.messages = messages;
        this.temperature = temperature;
        this.top_p = top_p;
        this.do_sample = do_sample;
        this.max_tokens = max_tokens;
        this.stream = stream;
        this.moduleName = moduleName;
        this.functionDescription = functionDescription;
        this.codeStyle = codeStyle;
        this.frontEndFramework = frontEndFramework;
    }

    // Getters
    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTop_p() {
        return top_p;
    }

    public boolean isDo_sample() {
        return do_sample;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public boolean isStream() {
        return stream;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public String getCodeStyle() {
        return codeStyle;
    }

    public String getFrontEndFramework() {
        return frontEndFramework;
    }
}