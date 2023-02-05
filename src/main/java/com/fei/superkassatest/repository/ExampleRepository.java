package com.fei.superkassatest.repository;

import com.fei.superkassatest.model.Example;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ExampleRepository extends JpaRepository<Example, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Example> findExampleById(Long id);
}
