package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CodeGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(CodeGenerationService.class);
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CodeGenerationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://open.bigmodel.cn/api/paas/v4/chat/completions").build();
    }

    public Mono<String> generateProgram(String moduleName, String functionDescription, String codeStyle, String frontEndFramework) {
        // 构造提示信息
        String prompt = String.format("模块名称：%s，功能描述：%s，代码风格：%s，前端框架：%s", moduleName, functionDescription, codeStyle, frontEndFramework);
        return generateCode(prompt, moduleName, functionDescription, codeStyle, frontEndFramework);
    }

    public Mono<String> generateCode(String prompt, String moduleName, String functionDescription, String codeStyle, String frontEndFramework) {
        // 构造请求体
        CodeGenerationRequest request = new CodeGenerationRequest(
                "glm-4-alltools",
                List.of(new Message("user", prompt)),
                0.7,
                0.8,
                true,
                4096,
                true,
                moduleName,
                functionDescription,
                codeStyle,
                frontEndFramework
        );

        // 发送请求并处理流式响应
        return webClient.post()
               .header("Content-Type", "application/json")
               .header("Authorization", "Bearer 44cbe9d466cc4860b073af7ac3000b73.EL9M3B1ZFodvV64u")
               .bodyValue(request)
               .retrieve()
               .bodyToFlux(String.class)
               .takeUntil(responseLine -> responseLine.contains("[DONE]")) // 直到接收到 [DONE] 结束流
               .filter(responseLine -> !responseLine.isEmpty() && !responseLine.equals("[DONE]")) // 过滤掉空行和 [DONE]
               .map(this::extractContentFromResponse) // 提取 content 字段
               .collect(StringBuilder::new, StringBuilder::append) // 拼接所有内容
               .map(StringBuilder::toString); // 转换为字符串
    }

    private String extractContentFromResponse(String responseLine) {
        try {
            // 解析 JSON 响应
            JsonNode jsonNode = objectMapper.readTree(responseLine);
            JsonNode choices = jsonNode.get("choices");
            if (choices != null && choices.isArray() && choices.size() > 0) {
                JsonNode delta = choices.get(0).get("delta");
                if (delta != null) {
                    JsonNode content = delta.get("content");
                    if (content != null) {
                        return content.asText(); // 提取 content 字段
                    }
                }
            }
        } catch (Exception e) {
            // 使用日志框架记录异常信息
            logger.error("Failed to parse JSON response: {}", responseLine, e);
        }
        return ""; // 如果解析失败，返回空字符串
    }
}
