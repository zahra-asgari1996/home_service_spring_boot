package ir.maktab.service.mapper;

import ir.maktab.data.domain.Customer;
import ir.maktab.dto.CustomerDto;

public interface CustomerMapper {
    Customer toCustomer(CustomerDto dto);

    CustomerDto toCustomerDto(Customer customer);
}
