package com.fei.superkassatest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fei.superkassatest.controller.ExampleController;
import com.fei.superkassatest.dto.InputDto;
import com.fei.superkassatest.dto.OutputDto;
import com.fei.superkassatest.service.ExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExampleController.class)
public class MockMvcTest {
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ExampleService service;

    @Autowired
    private MockMvc mvc;

    private InputDto inputDto;

    private OutputDto outputDto;

    @BeforeEach
    void init() {
        inputDto = InputDto.builder().id(1L).add(100).build();
        outputDto = new OutputDto(100);
    }

    @Test
    void postTest() throws Exception {
        when(service.post(any()))
                .thenReturn(outputDto);
        mvc.perform(post("/modify")
                        .content(mapper.writeValueAsString(inputDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(outputDto)));
    }

    @Test
    void postWithException() throws Exception {
        when(service.post(any()))
                .thenThrow(DataIntegrityViolationException.class);
        mvc.perform(post("/modify")
                .content(mapper.writeValueAsString(InputDto.builder().id(1L).build()))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(418));
    }
}
