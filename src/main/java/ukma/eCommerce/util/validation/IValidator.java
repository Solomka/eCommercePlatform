package ukma.eCommerce.util.validation;

import javax.validation.constraints.NotNull;

/**
 * describes validation responsibilities
 * @author Максим
 * */
public interface IValidator <T extends IValidateable> {
	/**
	 * Validates specified object and returns instance of {@linkplain ValidationResult}
	 * which contains result and in case of invalidity may contain
	 * localized message 
	 * */
	ValidationResult validate(@NotNull T t);

}
