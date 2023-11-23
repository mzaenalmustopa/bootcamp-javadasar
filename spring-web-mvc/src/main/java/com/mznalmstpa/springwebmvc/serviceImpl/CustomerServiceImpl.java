package com.mznalmstpa.springwebmvc.serviceImpl;

import com.mznalmstpa.springwebmvc.model.CustomerModel;
import com.mznalmstpa.springwebmvc.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private List<CustomerModel> customerModels = new ArrayList<>();


    public CustomerServiceImpl() {
        this.customerModels = customerModels;
        CustomerModel customer1 = new CustomerModel(1,1223344550L,"mstpa", "topa@gmail.com","Ciamis","628000900");
        CustomerModel customer2 = new CustomerModel(1,1223344550L,"mstpa", "znal@gmail.com","Tasik","6328000900");
        this.customerModels.add(customer2);
    }

    @Override
    public List<CustomerModel> gets() {
        return this.customerModels;
    }

    @Override
    public Optional<CustomerModel> getById(int id) {
       Optional <CustomerModel> result = this.customerModels.stream().filter(x -> x.getId() == id).findFirst();
        return result;
    }

    @Override
    public void save(CustomerModel request) {
        int lastId = this.customerModels.stream().mapToInt(CustomerModel::getId)
                .max().orElse(0);
        request.setId(lastId+1);
        this.customerModels.add(request);
    }

    @Override
    public void update(CustomerModel request, int id){
        int index = 0;
        CustomerModel oldData = null;
        for (int i = 0; i< this.customerModels.size(); i++){
            CustomerModel data = this.customerModels.get(i);
            if (data.getId() == id){
                index = i;
                oldData = data;

                break;
            }
        }
        // if null or not found
        if (oldData == null){
            return;
        }

        oldData.setAccountNo(request.getAccountNo());
        oldData.setName(request.getName());
        oldData.setEmail(request.getEmail());
        oldData.setPhoneNumber(request.getPhoneNumber());
        oldData.setAddress(request.getAddress());

        // replace customer list with new data
        this.customerModels.set(index, oldData);
    }

    @Override
    public void delete(int id) {
        int index = 0;
        CustomerModel oldData = null;
        for (int i = 0; i< this.customerModels.size(); i++){
            CustomerModel data = this.customerModels.get(i);
            if (data.getId() == id){
                index = i;
                oldData = data;

                break;
            }
        }
        if (oldData ==null){
            return;
        }

        this.customerModels.remove(index);
    }
}
