package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

import java.util.Objects;

/**
 * <p>
 * Describes currency type
 * </p>
 * Created by Максим on 10/15/2016.
 */
public enum Currency {

	UAH("Hryvnia", "uah"), EU("Euro", "eu"), USD("United States Dollar", "usd");

	private final String fullName;
	private final String shortName;

	Currency(String fullName, String shortName) {
		this.fullName = Objects.requireNonNull(fullName);
		this.shortName = Objects.requireNonNull(shortName);
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	@Override
	public String toString() {
		return fullName;
	}
}
