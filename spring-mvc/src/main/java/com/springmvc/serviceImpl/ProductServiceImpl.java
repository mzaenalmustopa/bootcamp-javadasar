package com.springmvc.serviceImpl;

import com.springmvc.entity.ProductEntity;
import com.springmvc.model.ProductModel;
import com.springmvc.repository.ProductRepo;
import com.springmvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo){
        this.productRepo = productRepo;
    }


    @Override
    public List<ProductEntity> getAll() {
        return this.productRepo.findAll();
    }

    @Override
    public ProductEntity getById(Integer id) {
        return this.productRepo.findById(id).orElse(null);
    }

    @Override
    public ProductEntity save(ProductModel request) {
        ProductEntity result = new ProductEntity();

        BeanUtils.copyProperties(request, result);
        try{
            this.productRepo.save(result);
            log.info("save product to database successfully");
            return result;
        }catch (Exception e){
            log.error("save to database failed, error:{}",e.getMessage());
            return null;
        }
    }

    @Override
    public ProductEntity update(Integer id, ProductModel request) {
        return null;
    }

    @Override
    public ProductEntity delete(Integer id) {
        return null;
    }
}
