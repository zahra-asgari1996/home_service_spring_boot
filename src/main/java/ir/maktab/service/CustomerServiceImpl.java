package ir.maktab.service;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.OrderDto;
import ir.maktab.service.exception.DuplicatedEmailAddressException;
import ir.maktab.service.exception.InvalidPassword;
import ir.maktab.service.exception.NotFoundCustomerException;
import ir.maktab.service.mapper.CustomerMapper;
import ir.maktab.service.mapper.OrderMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;
    private final MessageSource messageSource;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, OrderMapper orderMapper, MessageSource messageSource) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
        this.messageSource = messageSource;
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto dto) throws DuplicatedEmailAddressException {
        Optional<Customer> customer = customerRepository.findByEmail(dto.getEmail());
        if (customer.isPresent()) {
            throw new DuplicatedEmailAddressException(messageSource.getMessage("duplicated.email.address",null,new Locale("fa_ir")));
        }
        customerRepository.save(customerMapper.toCustomer(dto));
        return dto;
    }

    @Override
    public void updateCustomer(CustomerDto dto) {
        customerRepository.save(customerMapper.toCustomer(dto));

    }

    @Override
    public void deleteCustomer(CustomerDto dto) {
        customerRepository.delete(customerMapper.toCustomer(dto));

    }

    @Override
    public List<CustomerDto> fetchAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findByEmail(String email) throws NotFoundCustomerException {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            return customerMapper.toCustomerDto(customer.get());
        }
        throw new NotFoundCustomerException(messageSource.getMessage("not.found.customer",null,new Locale("fa_ir")));
    }

    @Override
    public CustomerDto loginCustomer(CustomerDto dto) throws InvalidPassword, NotFoundCustomerException {
        Optional<Customer> customer = customerRepository.findByEmail(dto.getEmail());
        if (customer.isPresent()) {
            if (customer.get().getPassword().equals(dto.getPassword())) {
                return customerMapper.toCustomerDto(customer.get());
            } else {
                throw new InvalidPassword(messageSource.getMessage("invalid.password",null,new Locale("fa_ir")));
            }
        } else {
            throw new NotFoundCustomerException(messageSource.getMessage("not.found.customer",null,new Locale("fa_ir")));
        }
    }

    @Override
    public void changePassword(CustomerDto dto) {
        Optional<Customer> customer = customerRepository.findByEmail(dto.getEmail());
        Customer customer1 = customer.get();
        customer1.setPassword(dto.getPassword());
        customerRepository.save(customer1);
    }

    @Override
    public List<OrderDto> showOrders(CustomerDto dto) {
        Optional<Customer> customer = customerRepository.findByEmail(dto.getEmail());
        return customer.get().getOrders().stream().map(i->orderMapper.toOrderDto(i)).collect(Collectors.toList());
    }
}
