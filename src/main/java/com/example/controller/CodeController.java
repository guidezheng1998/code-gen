package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
    private final CodeGenerationService codeGenerationService;

    public CodeController(CodeGenerationService codeGenerationService) {
        this.codeGenerationService = codeGenerationService;
    }

    @PostMapping("/generateProgram")
    public Mono<String> generateProgram(@RequestBody Map<String, String> params) {
        try {
            String moduleName = params.get("moduleName");
            String functionDescription = params.get("functionDescription");
            String codeStyle = params.get("codeStyle");
            String frontEndFramework = params.get("frontEndFramework");

            // 参数校验
            if (moduleName == null || functionDescription == null) {
                logger.error("生成代码请求缺少必要参数：moduleName 或 functionDescription");
                return Mono.error(new IllegalArgumentException("生成代码请求缺少必要参数：moduleName 或 functionDescription"));
            }

            return codeGenerationService.generateProgram(moduleName, functionDescription, codeStyle, frontEndFramework);
        } catch (Exception e) {
            logger.error("生成代码时发生错误", e);
            return Mono.error(e);
        }
    }
}
