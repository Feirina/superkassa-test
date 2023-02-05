package com.fei.superkassatest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InputDto {
    @NotNull
    private Long id;

    @NotNull
    private Integer add;
}
