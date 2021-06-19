package ir.maktab.service.mapper;

import ir.maktab.data.domain.Customer;
import ir.maktab.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
//    private final CommentMapper commentMapper;
//    private final OrderMapper orderMapper;
//
//    public CustomerMapperImpl(CommentMapper commentMapper, OrderMapper orderMapper) {
//        this.commentMapper = commentMapper;
//        this.orderMapper = orderMapper;
//    }

    @Override
    public Customer toCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setRole(dto.getRole());
        customer.setSituation(dto.getSituation());
        customer.setDate(dto.getDate());
        customer.setCredit(dto.getCredit());
//        customer.setComments(
//                dto.getComments().stream().map
//                        (i->commentMapper.toComment(i))
//                        .collect(Collectors.toList()));
//
//        customer.setOrders(
//                dto.getOrders().stream().map
//                        (i->orderMapper.toOrder(i))
//                        .collect(Collectors.toList()));

        return customer;
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());
        dto.setDate(customer.getDate());
//        dto.setComments(customer.getComments().stream().map(i->commentMapper.toCommentDto(i)).collect(Collectors.toList()));
//        dto.setOrders(customer.getOrders().stream().map(i->orderMapper.toOrderDto(i)).collect(Collectors.toList()));
        dto.setRole(customer.getRole());
        dto.setSituation(customer.getSituation());
        dto.setCredit(customer.getCredit());
        return dto;
    }
}
