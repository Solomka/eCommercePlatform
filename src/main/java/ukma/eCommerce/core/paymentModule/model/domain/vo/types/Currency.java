package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

import java.util.Objects;

/**
 * <p>
 *     Describes currency type
 * </p>
 * Created by Максим on 10/15/2016.
 */
public enum Currency {

    UAH("Hryvnia"), EU("Euro"), USD("United States Dollar");

    private final String fullName;

    Currency(String fullName) {
        this.fullName = Objects.requireNonNull(fullName);
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
