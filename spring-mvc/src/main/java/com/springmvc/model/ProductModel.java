package com.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private Integer id;
    private String code;
    private String name;
    private String category;
    private String description;
    private Integer price;
    private String stock;
}
