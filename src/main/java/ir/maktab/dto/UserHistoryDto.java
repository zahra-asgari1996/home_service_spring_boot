package ir.maktab.dto;

import java.util.Date;

public class UserHistoryDto {
    private Date startDate;
    private Date endDate;
    private Integer maxNumberOfOrders;
    private Integer minNumberOfOrders;
    private Integer maxNumberOfOffers;
    private Integer minNumberOfOffers;

    public Date getStartDate() {
        return startDate;
    }

    public UserHistoryDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UserHistoryDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getMaxNumberOfOrders() {
        return maxNumberOfOrders;
    }

    public UserHistoryDto setMaxNumberOfOrders(Integer maxNumberOfOrders) {
        this.maxNumberOfOrders = maxNumberOfOrders;
        return this;
    }

    public Integer getMinNumberOfOrders() {
        return minNumberOfOrders;
    }

    public UserHistoryDto setMinNumberOfOrders(Integer minNumberOfOrders) {
        this.minNumberOfOrders = minNumberOfOrders;
        return this;
    }

    public Integer getMaxNumberOfOffers() {
        return maxNumberOfOffers;
    }

    public UserHistoryDto setMaxNumberOfOffers(Integer maxNumberOfOffers) {
        this.maxNumberOfOffers = maxNumberOfOffers;
        return this;
    }

    public Integer getMinNumberOfOffers() {
        return minNumberOfOffers;
    }

    public UserHistoryDto setMinNumberOfOffers(Integer minNumberOfOffers) {
        this.minNumberOfOffers = minNumberOfOffers;
        return this;
    }
}
