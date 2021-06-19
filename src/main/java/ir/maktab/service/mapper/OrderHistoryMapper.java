package ir.maktab.service.mapper;

import ir.maktab.data.domain.OrderHistory;
import ir.maktab.dto.OrderHistoryDto;

public interface OrderHistoryMapper {

    OrderHistory toOrderHistory(OrderHistoryDto dto);
    OrderHistoryDto toOrderHistoryDto(OrderHistory orderHistory);
}
