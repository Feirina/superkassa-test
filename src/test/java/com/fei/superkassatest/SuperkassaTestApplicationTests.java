package com.fei.superkassatest;

import com.fei.superkassatest.controller.ExampleController;
import com.fei.superkassatest.dto.InputDto;
import com.fei.superkassatest.model.Example;
import com.fei.superkassatest.model.MyJson;
import com.fei.superkassatest.repository.ExampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SuperkassaTestApplicationTests {
    @Autowired
    private ExampleRepository repository;

    @Autowired
    private ExampleController controller;

    private InputDto inputDto;

    @BeforeEach
    void init() {
        inputDto = InputDto.builder().id(1L).build();
    }

    @Test
    void notValidInputDto() {
        assertThrows(Throwable.class, ()->controller.postExample(inputDto));
    }

    @Test
    void postExample() {
        inputDto.setAdd(100);
        repository.save(Example.builder().obj((new MyJson(0))).build());
        controller.postExample(inputDto);
        assertEquals(100, repository.findById(inputDto.getId()).get().getObj().getCurrent());
    }
}
