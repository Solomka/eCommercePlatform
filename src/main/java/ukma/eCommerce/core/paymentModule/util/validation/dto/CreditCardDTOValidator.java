package ukma.eCommerce.core.paymentModule.util.validation.dto;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.util.TextUtils;

import static ukma.eCommerce.util.validation.ValidationUtil.CARD_CVV;
import static ukma.eCommerce.util.validation.ValidationUtil.CARD_NUMBER_DELIM;

/**
 * <p>
 * Custom validator for {@link CreditCardDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class CreditCardDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CreditCardDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final CreditCardDTO card = (CreditCardDTO) o;

        if (TextUtils.nullOrEmpty(card.getNumber())) {
            errors.rejectValue("number", "error.card.dto.number", "Credit card number wasn't specified");
        } else if (!CARD_NUMBER_DELIM.matcher(card.getNumber()).matches()) {
            errors.rejectValue("number", "error.card.dto.number", String.format("Credit card number %s isn't valid",
                    card.getNumber()));
        }

        if (TextUtils.nullOrEmpty(card.getCvv())) {
            errors.rejectValue("cvv", "error.card.dto.cvv", "Credit card cvv wasn't specified");
        } else if (!CARD_CVV.matcher(card.getCvv()).matches()) {
            errors.rejectValue("cvv", "error.card.dto.cvv", String.format("Credit card cvv %s isn't valid",
                    card.getCvv()));
        }

        final DateTime expDate = card.getExpirationDate();

        if (expDate == null) {
            errors.rejectValue("expirationDate", "error.card.dto.expirationDate",
                    "Credit card expire date wasn't specified or broken");
        } else if (DateTime.now().compareTo(expDate) > 0) {
            // card is expired
            errors.rejectValue("expirationDate", "error.card.dto.expirationDate", "Credit card is expired");
        }
    }
}
