package ukma.eCommerce.util.validation;

import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.util.TextUtils;

/**
 * @author Max
 */
final class SellerValidator extends BaseUserValidator<Seller> {

	SellerValidator() {
	}

	@Override
	public ValidationResult validate(Seller seller) {

		final ValidationResult superValidation = super.validate(seller);

		if (!superValidation.isValid()) {
			return superValidation;
		}

		if (!TextUtils.nullOrEmpty(seller.getCompany()) && !PLAIN_STR_PATTERN.matcher(seller.getCompany()).matches()) {
			return ValidationResult
					.createInvalidResult(String.format("Company name '%s' is invalid", seller.getCompany()));
		}

		return ValidationResult.createValidResult();
	}

}
