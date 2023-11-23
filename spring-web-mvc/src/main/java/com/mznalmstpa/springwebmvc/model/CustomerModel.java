package com.mznalmstpa.springwebmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private int id;
    private Long accountNo;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
