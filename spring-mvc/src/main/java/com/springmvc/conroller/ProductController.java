package com.springmvc.conroller;

import com.springmvc.entity.ProductEntity;
import com.springmvc.model.ProductModel;
import com.springmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("product/index");

        List<ProductEntity> dataProduct = productService.getAll();
        view.addObject("listProduct", dataProduct);

        int jmlData = dataProduct.size();
        view.addObject("jmlData", jmlData);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("product/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute ProductModel request){
        this.productService.save(request);
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ProductEntity productEntity = this.productService.getById(id);
        if (productEntity == null){
            return new ModelAndView("redirect:/product");
        }
        ModelAndView view = new ModelAndView("product/edit");
        view.addObject("product", productEntity);
        return view;
    }
}
