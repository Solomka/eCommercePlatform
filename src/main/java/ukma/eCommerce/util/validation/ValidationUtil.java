package ukma.eCommerce.util.validation;

import javax.validation.Validation;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * <p>
 * An utility class for validation
 * </p>
 * Created by Максим on 10/15/2016.
 */
public final class ValidationUtil {

    private ValidationUtil() {
        throw new RuntimeException("shouldn't be called");
    }

    /**
     * Allowed delimiters in card number
     */
    public static final Pattern CARD_NUMBER_DELIMITERS = Pattern.compile("[\\\\s-]");
    /**
     * Credit card number validation pattern
     */
    public static final Pattern CARD_NUMBER_DELIM = Pattern.compile(
            "^\\d{3}%delim%?\\d{3}%delim%?\\d{3}%delim%?\\d{3}$".
                    replaceAll("%delim%", ValidationUtil.CARD_NUMBER_DELIMITERS.pattern())
    );
    /**
     * Credit card number validation pattern
     */
    public static final Pattern CARD_NUMBER = Pattern.compile("^\\d{12}$");
    /**
     * Credit cvv validation pattern
     */
    public static final Pattern CARD_CVV = Pattern.compile("^\\d{3}$");
    /**
     * Plain string. Don't forget to specify length!
     * */
    public static final String PLAIN_STR = "[a-zA-z]{%d,%d}";
    /**
     * Phone pattern
     * */
    public static final String PHONE_PATTERN_DELIM = "^\\+((\\d{9,14})|((\\(\\d{3,4}\\))\\d{5,10}))$";
    /**
     * Phone pattern
     * */
    public static final String PHONE_PATTERN = "^\\+\\d{9,16}$";
    /**
     * Login pattern
     * */
    public static final String LOGIN_PATTERN = "^[\\d\\w]{4,15}$";// TODO: 10/16/2016 improve
    /**
     * Password pattern
     * */
    public static final String PASSWORD_PATTERN = "^[\\d\\w]{6,28}$";// TODO: 10/16/2016 improve

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    /**
     * Common email pattern
     * */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";


    public static <T> boolean isValid(T t) {
        return Validation.buildDefaultValidatorFactory().getValidator().validate(Objects.requireNonNull(t)).isEmpty();
    }

}
