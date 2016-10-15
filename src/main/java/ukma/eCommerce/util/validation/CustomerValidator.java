package ukma.eCommerce.util.validation;

import ukma.eCommerce.core.userModule.model.domain.bo.Customer;

/**
 * @author Max
 */
class CustomerValidator extends BaseUserValidator<Customer> {

	CustomerValidator() {
	}

	@Override
	public ValidationResult validate(Customer seller) {

		final ValidationResult superValidation = super.validate(seller);

		if (!superValidation.isValid()) {
			return superValidation;
		}

		if (seller.getBonus() < 0) {
			return ValidationResult
					.createInvalidResult(String.format("Bonuses should greater than 0, was %d", seller.getBonus()));
		}

		return ValidationResult.createValidResult();
	}

}
