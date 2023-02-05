package com.fei.superkassatest.controller;

import com.fei.superkassatest.dto.InputDto;
import com.fei.superkassatest.dto.OutputDto;
import com.fei.superkassatest.service.ExampleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExampleController {
    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @PostMapping("/modify")
    public OutputDto postExample(@Valid @RequestBody InputDto inputDto) {
        log.info("post example with id {}, add {}", inputDto.getId(), inputDto.getAdd());
        return exampleService.post(inputDto);
    }
}
