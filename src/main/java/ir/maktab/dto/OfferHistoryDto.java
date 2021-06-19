package ir.maktab.dto;

import ir.maktab.data.enums.OfferSituation;

import java.util.Date;

public class OfferHistoryDto {
    private String customerEmail;
    private String expertEmail;
    private Date startDate;
    private Date endDate;
    private OfferSituation situation;
    private Double maxOfferPrice;
    private Double minOfferPrice;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OfferHistoryDto setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public String getExpertEmail() {
        return expertEmail;
    }

    public OfferHistoryDto setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public OfferHistoryDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public OfferHistoryDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public OfferSituation getSituation() {
        return situation;
    }

    public OfferHistoryDto setSituation(OfferSituation situation) {
        this.situation = situation;
        return this;
    }

    public Double getMaxOfferPrice() {
        return maxOfferPrice;
    }

    public OfferHistoryDto setMaxOfferPrice(Double maxOfferPrice) {
        this.maxOfferPrice = maxOfferPrice;
        return this;
    }

    public Double getMiOfferPrice() {
        return minOfferPrice;
    }

    public OfferHistoryDto setMiOfferPrice(Double miOfferPrice) {
        this.minOfferPrice = miOfferPrice;
        return this;
    }
}
