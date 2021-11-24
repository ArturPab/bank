package pl.pabjan.bankmanagementsystem.exceptions;

//Class for exception handling
public class BankCustomerException extends RuntimeException {
    public BankCustomerException(String message) {
        super(message);
    }
}
