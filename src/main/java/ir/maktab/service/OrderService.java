package ir.maktab.service;

import ir.maktab.dto.*;
import ir.maktab.service.exception.*;

import java.util.List;

public interface OrderService {
    void saveNewOrder(OrderDto dto) throws NotFoundCustomerException;

    void deleteOrder(OrderDto dto);

    OrderDto updateOrder(OrderDto dto);

    List<OrderDto> fetchAllOrders();

    void selectOffer(OrderDto orderDto, OfferDto dto) throws NotFoundOfferForOrder;

    List<OrderDto> findOrdersBaseOnExpertSubServicesAndSituation(ExpertDto expertDto) throws AccessException, NotFoundOrderForExpertException;

    OrderDto findById(Integer id) throws NotFoundOrderException;

    List<OrderDto> findByExpert(ExpertDto dto) throws NotFoundOrderException;
    List<OrderDto> findByCustomer(CustomerDto dto) throws NotFoundOrderException, NotFoundCustomerException;

    void endOfWork(Integer id) throws NotFoundOrderException;

    void confirmPay(Integer id) throws NotFoundOrderException;

    void startWork(Integer id) throws NotFoundOrderException;

    List<OrderDto> filterOrders(FilterOrdersDto dto) throws NotFoundOrderException;
    List<String> situations();

    List<OrderDto> filterUserOrders(FilterSpecialUserOrdersDto dto) throws NotFoundOrderException;
}
