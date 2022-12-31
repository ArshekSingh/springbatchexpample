package com.springbatchexp.springbatchexpample.service;

import com.springbatchexp.springbatchexpample.entity.Customers;
import com.springbatchexp.springbatchexpample.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customers> getDetails() {
        return customerRepo.findAll();
    }
}
