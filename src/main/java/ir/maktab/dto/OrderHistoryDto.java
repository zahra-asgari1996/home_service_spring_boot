package ir.maktab.dto;

import ir.maktab.data.enums.OrderSituation;

public class OrderHistoryDto {
    private int id;
    private OrderDto orderDto;
    private OrderSituation orderSituation;

    public int getId() {
        return id;
    }

    public OrderHistoryDto setId(int id) {
        this.id = id;
        return this;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public OrderHistoryDto setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
        return this;
    }

    public OrderSituation getOrderSituation() {
        return orderSituation;
    }

    public OrderHistoryDto setOrderSituation(OrderSituation orderSituation) {
        this.orderSituation = orderSituation;
        return this;
    }
}
