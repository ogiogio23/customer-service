package com.ogiogio.customerservice.service;

import com.ogiogio.customerservice.contract.CustomerService;
import com.ogiogio.customerservice.model.BaseResponse;
import com.ogiogio.customerservice.model.Customer;
import com.ogiogio.customerservice.model.CustomerDetail;
import com.ogiogio.customerservice.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private JdbcTemplate jdbctemplate;

    @Autowired
    public CustomerServiceImpl(DataSource dataSource){
        this.jdbctemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BaseResponse addCustomer(Customer customer){
        BaseResponse baseResponse = new BaseResponse();
        try{
            String sql = "INSERT INTO customer(first_name, last_name, phone_number, username, email) VALUE(?,?,?,?,?)";
            int customerInfo = jdbctemplate.update(sql, new Object[]{customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getUsername(), customer.getEmail()});
            return processQuery(customerInfo);

        }
        catch(Exception e){
            e.printStackTrace();
            baseResponse.setResponseCode("10002");
            baseResponse.setResponseMessage(e.getCause().getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateCustomer(Customer customer){
        BaseResponse baseResponse = new BaseResponse();
        try{
            String sql = "UPDATE customer SET first_name=?, last_name=?, phone_number=?, username=? WHERE email=?";
            int customerInfo = jdbctemplate.update(sql, new Object[]{customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getUsername(), customer.getEmail()});
            return processQuery(customerInfo);
        }
        catch (Exception e){
            e.printStackTrace();
            baseResponse.setResponseCode("10002");
            baseResponse.setResponseMessage(e.getCause().getMessage());
        }
        return baseResponse;

    }

    @Override
    public BaseResponse deleteCustomer(String email){
        BaseResponse baseResponse = new BaseResponse();
        try {
            String sql = "DELETE FROM customer WHERE email = ?";
            int customerInfo = jdbctemplate.update(sql, new Object[]{email});
            return processQuery(customerInfo);
        }
        catch(Exception e){
            e.printStackTrace();
            baseResponse.setResponseCode("10002");
            baseResponse.setResponseMessage(e.getCause().getMessage());
        }
        return baseResponse;
    }

    public BaseResponse processQuery(int customerInfo){
        BaseResponse baseResponse = new BaseResponse();
        if(customerInfo==0){
            baseResponse.setResponseCode("10001");
            baseResponse.setResponseMessage("Error Occurred");
        }
        else{
            baseResponse.setResponseCode("00");
            baseResponse.setResponseMessage("Approved");
        }
        return baseResponse;
    }

    private class CustomerRowMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setPhoneNumber(resultSet.getString("phone_number"));
            customer.setUsername(resultSet.getString("username"));
            customer.setEmail(resultSet.getString("email"));
            return customer;
        }
    }

    @Override
    public Customers getCustomers(){
        Customers customers = new Customers();
        try{
           String sql = "SELECT * FROM customer";
           List<Customer> customer = jdbctemplate.query(sql, new CustomerRowMapper());
           if(customer.isEmpty() || customer == null){
               customers.setResponseCode("10001");
               customers.setResponseMessage("No Records Found");
               return customers;
           }
           customers.setResponseCode("00");
           customers.setResponseMessage("Approved");
           customers.setCustomers(customer);
        }
        catch(Exception e){
            e.printStackTrace();
            customers.setResponseCode("10002");
            customers.setResponseMessage(e.getCause().getMessage());
        }
        return customers;
    }

    @Override
    public CustomerDetail getCustomerDetail(String email){
        CustomerDetail customerDetail = new CustomerDetail();
        try{
            String sql = "SELECT * FROM customer WHERE email = ?";
            Customer customer = jdbctemplate.queryForObject(sql, new Object[]{email}, new CustomerRowMapper());
            if(customer==null){
                customerDetail.setResponseCode("10001");
                customerDetail.setResponseMessage("Error Occurred");
            }
            customerDetail.setResponseCode("00");
            customerDetail.setResponseMessage("Successful");
            customerDetail.setCustomerDetail(customer);

        }
        catch (Exception e){
            e.printStackTrace();
            customerDetail.setResponseCode("10002");
            customerDetail.setResponseMessage("No record found");
        }
        return customerDetail;
    }
}
