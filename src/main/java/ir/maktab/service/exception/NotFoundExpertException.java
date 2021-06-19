package ir.maktab.service.exception;

public class NotFoundExpertException extends Exception {
    public NotFoundExpertException(String expert_is_not_available) {
        super(expert_is_not_available);
    }
}
