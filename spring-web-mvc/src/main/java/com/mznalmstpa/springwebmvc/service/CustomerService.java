package com.mznalmstpa.springwebmvc.service;

import com.mznalmstpa.springwebmvc.model.CustomerModel;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerModel> gets();
    Optional<CustomerModel> getById(int id);
    void save (CustomerModel request);
    void update (CustomerModel request, int id);
    void delete(int id);
}
