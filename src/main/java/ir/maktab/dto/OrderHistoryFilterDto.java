package ir.maktab.dto;

import ir.maktab.data.enums.OrderSituation;

import java.util.Date;

public class OrderHistoryFilterDto {

    private OrderSituation situation;
    private String serviceName;
    private String subServiceName;
    private Date startDate;
    private Date endDate;

    public OrderSituation getSituation() {
        return situation;
    }

    public OrderHistoryFilterDto setSituation(OrderSituation situation) {
        this.situation = situation;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public OrderHistoryFilterDto setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public OrderHistoryFilterDto setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public OrderHistoryFilterDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public OrderHistoryFilterDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }
}
