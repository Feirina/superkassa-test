package com.fei.superkassatest.service;

import com.fei.superkassatest.dto.InputDto;
import com.fei.superkassatest.dto.OutputDto;
import com.fei.superkassatest.model.Example;
import com.fei.superkassatest.model.MyJson;
import com.fei.superkassatest.repository.ExampleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleServiceImpl implements ExampleService {
    private final ExampleRepository repository;

    public ExampleServiceImpl(ExampleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public OutputDto post(InputDto inputDto) {
        Example example = repository.findExampleById(inputDto.getId()).orElseThrow();
        MyJson json = new MyJson(example.getObj().getCurrent() + inputDto.getAdd());
        example.setObj(json);
        return new OutputDto(json.getCurrent());
    }
}
