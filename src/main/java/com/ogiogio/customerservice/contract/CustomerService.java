package com.ogiogio.customerservice.contract;


import com.ogiogio.customerservice.model.BaseResponse;
import com.ogiogio.customerservice.model.Customer;
import com.ogiogio.customerservice.model.CustomerDetail;
import com.ogiogio.customerservice.model.Customers;

public interface CustomerService {
    BaseResponse addCustomer(Customer customer);
    BaseResponse updateCustomer(Customer customer);
    BaseResponse deleteCustomer(String email);
    Customers getCustomers();
    CustomerDetail getCustomerDetail(String email);
}
