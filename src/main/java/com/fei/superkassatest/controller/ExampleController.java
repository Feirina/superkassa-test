package com.fei.superkassatest.controller;

import com.fei.superkassatest.dto.InputDto;
import com.fei.superkassatest.dto.OutputDto;
import com.fei.superkassatest.service.ExampleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @PostMapping("/modify")
    public OutputDto postExample(@Valid @RequestBody InputDto inputDto) {
        return exampleService.post(inputDto);
    }
}
