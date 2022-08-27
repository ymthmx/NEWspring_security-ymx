package com.ymxexample.domain;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String username;
    private String password;
    private Integer valid;
}
