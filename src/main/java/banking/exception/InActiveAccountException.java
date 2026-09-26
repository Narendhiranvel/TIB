package banking.exception;

public class InActiveAccountException extends RuntimeException {

    public InActiveAccountException(String message) {
        super(message);
    }
}
