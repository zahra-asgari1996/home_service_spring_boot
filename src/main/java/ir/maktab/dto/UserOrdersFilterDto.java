package ir.maktab.dto;

import ir.maktab.data.enums.OrderSituation;

import java.util.Date;

public class UserOrdersFilterDto {
    private Integer id;
    private Double maxPrice;
    private Double minPrice;
    private Date startDate;
    private Date endDate;
    private OrderSituation situation;

    public Integer getId() {
        return id;
    }

    public UserOrdersFilterDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public UserOrdersFilterDto setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public UserOrdersFilterDto setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public UserOrdersFilterDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UserOrdersFilterDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public OrderSituation getSituation() {
        return situation;
    }

    public UserOrdersFilterDto setSituation(OrderSituation situation) {
        this.situation = situation;
        return this;
    }
}
