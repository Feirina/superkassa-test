package com.fei.superkassatest.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyJson implements Serializable {
    private Integer current;
}
