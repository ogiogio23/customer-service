package com.ogiogio.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetail extends BaseResponse {
    private Customer customerDetail;

    public Customer getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(Customer customerDetail) {
        this.customerDetail = customerDetail;
    }
}
