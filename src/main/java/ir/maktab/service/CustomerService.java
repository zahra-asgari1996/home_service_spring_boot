package ir.maktab.service;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.exception.DuplicatedEmailAddressException;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundCustomerException;

import java.util.List;

public interface CustomerService {
    CustomerDto saveNewCustomer(CustomerDto dto) throws DuplicatedEmailAddressException;

    void updateCustomer(CustomerDto dto);

    void deleteCustomer(CustomerDto dto);

    List<CustomerDto> fetchAllCustomers();

    CustomerDto findByEmail(String email) throws NotFoundCustomerException;

    CustomerDto loginCustomer(CustomerDto dto) throws InvalidPassword, NotFoundCustomerException;

    void changePassword(CustomerDto dto);

    List<OrderDto> showOrders(CustomerDto dto);
}
