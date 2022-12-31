package com.springbatchexp.springbatchexpample.controller;

import com.springbatchexp.springbatchexpample.entity.Customers;
import com.springbatchexp.springbatchexpample.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService service;

    @GetMapping("/getDetails")
    public List<Customers> getDetails() {
       return service.getDetails();
    }

}
