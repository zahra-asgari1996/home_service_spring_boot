package ir.maktab.service;

import ir.maktab.data.repository.OrderHistoryRepository;
import ir.maktab.dto.OrderHistoryDto;
import ir.maktab.service.mapper.OrderHistoryMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderHistoryMapper orderHistoryMapper;

    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepository, OrderHistoryMapper orderHistoryMapper) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.orderHistoryMapper = orderHistoryMapper;
    }

    @Override
    public void save(OrderHistoryDto dto) {
        orderHistoryRepository.save(orderHistoryMapper.toOrderHistory(dto));
    }
}
