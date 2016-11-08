package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

import java.util.Objects;

/**
 * <p>
 * Describes currency type
 * </p>
 * Created by Максим on 10/15/2016.
 */
public enum Currency {

    UAH("Hryvnia", "uah", 1), EU("Euro", "eu", 2), USD("United States Dollar", "usd", 3);

    private final String fullName;
    private final String shortName;
    private final int id;

    Currency(String fullName, String shortName, int id) {
        this.fullName = Objects.requireNonNull(fullName);
        this.shortName = Objects.requireNonNull(shortName);
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getId() {
        return id;
    }

    public static Currency forId(int id) {
        for (final Currency currency : Currency.values()) {
            if (currency.getId() == id) return currency;
        }
        return null;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
