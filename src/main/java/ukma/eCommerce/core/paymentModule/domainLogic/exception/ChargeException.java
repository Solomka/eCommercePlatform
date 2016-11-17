package ukma.eCommerce.core.paymentModule.domainLogic.exception;

/**
 * Created by Максим on 11/17/2016.
 */
public class ChargeException extends Exception {

    public ChargeException() {
    }

    public ChargeException(String message) {
        super(message);
    }

    public ChargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChargeException(Throwable cause) {
        super(cause);
    }

    public ChargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
