package ir.maktab.service.exception;

public class NotEnoughAccountBalance extends Exception {
    public NotEnoughAccountBalance(String s) {
        super(s);
    }
}
