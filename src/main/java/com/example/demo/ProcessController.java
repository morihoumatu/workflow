package com.example.demo;

import org.activiti.engine.RuntimeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    private final RuntimeService runtimeService;

    public ProcessController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("/start")
    public String startProcess() {
        var pi = runtimeService.startProcessInstanceByKey("hello");
        return "Started: " + pi.getId();
    }
}
