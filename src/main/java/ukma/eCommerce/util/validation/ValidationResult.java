package ukma.eCommerce.util.validation;

import javax.validation.constraints.Null;

/**
 * Class which describes validation result and
 * may contain validation failure message
 * @author Максим
 * */
public final class ValidationResult {
	
	private final boolean isValid;
	private final String message;
	
	private ValidationResult(boolean isValid, String message) {
		this.isValid = isValid;
		this.message = message;
	}
	
	static ValidationResult createValidResult() {
		return new ValidationResult(true, null);
	}
	
	static ValidationResult createInvalidResult(@Null String message) {
		return new ValidationResult(false, message);
	}

	public boolean isValid() {
		return isValid;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ValidationResult [isValid=" + isValid + ", message=" + message + "]";
	}
	
}
