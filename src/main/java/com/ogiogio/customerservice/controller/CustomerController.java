package com.ogiogio.customerservice.controller;

import com.ogiogio.customerservice.contract.CustomerService;
import com.ogiogio.customerservice.model.BaseResponse;
import com.ogiogio.customerservice.model.Customer;
import com.ogiogio.customerservice.model.CustomerDetail;
import com.ogiogio.customerservice.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @RequestMapping(value ="/customer/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addCustomer(@RequestBody@Validated Customer customer){
        return this.customerService.addCustomer(customer);
    }
    @RequestMapping(value ="/customer/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateCustomer(@RequestBody@Validated Customer customer){
        return this.customerService.updateCustomer(customer);
    }
    @RequestMapping(value ="/customer/delete/{email}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteCustomer(@PathVariable("email") String email) {
        return this.customerService.deleteCustomer(email);
    }
    @RequestMapping(value ="/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customers getCustomers() {
        return this.customerService.getCustomers();
    }
    @RequestMapping(value ="/customer-detail/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDetail getCustomerDetail(@PathVariable("email") String email) {
        return this.customerService.getCustomerDetail(email);
    }
}
