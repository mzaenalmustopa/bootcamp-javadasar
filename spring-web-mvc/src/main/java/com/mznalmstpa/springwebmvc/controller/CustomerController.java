package com.mznalmstpa.springwebmvc.controller;

import com.mznalmstpa.springwebmvc.model.CustomerModel;
import com.mznalmstpa.springwebmvc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("customer/index");
        //get from service
        List<CustomerModel> data = service.gets();
        // send data to view
        view.addObject("dataList", data);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("customer/add");
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute CustomerModel request){
        // call save from service
        service.save(request);
        //redirect to index
        return new ModelAndView("redirect:/customer");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit (@PathVariable("id") int id){
        ModelAndView view = new ModelAndView("customer/edit");
        CustomerModel data = service.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer");
        }
        view.addObject("data",data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute CustomerModel request){
        // call update form service
       service.update(request, request.getId());
       return new ModelAndView("redirect:/customer");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        service.delete(id);
        return "redirect:/customer";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id){
        ModelAndView view = new ModelAndView("customer/detail");
        // get data from service
        CustomerModel data = service.getById(id).orElse(null);
        if (data == null){
            return new ModelAndView("redirect:/customer");
        }
        // send data to view
        view.addObject("data", data);
        return view;
    }

}
