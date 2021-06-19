package ir.maktab.service.exception;

public class NotFoundOfferForOrder extends Exception {
    public NotFoundOfferForOrder(String notFoundOfferForOrder) {
        super(notFoundOfferForOrder);
    }
}
