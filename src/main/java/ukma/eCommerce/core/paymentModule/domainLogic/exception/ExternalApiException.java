package ukma.eCommerce.core.paymentModule.domainLogic.exception;

/**
 * <p>
 *     Describes external exception which occurred, e.g. external
 *     service is not available
 * </p>
 * Created by Максим on 11/17/2016.
 */
public class ExternalApiException extends Exception {

    public ExternalApiException() {
        super();
    }

    public ExternalApiException(String message) {
        super(message);
    }

    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalApiException(Throwable cause) {
        super(cause);
    }
}
