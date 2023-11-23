package com.springmvc.service;

import com.springmvc.entity.ProductEntity;
import com.springmvc.model.ProductModel;


import java.util.List;

public interface ProductService {
    List<ProductEntity> getAll();
    ProductEntity getById(Integer id);
    ProductEntity save(ProductModel request);
    ProductEntity update(Integer id, ProductModel request);
    ProductEntity delete(Integer id);
}
