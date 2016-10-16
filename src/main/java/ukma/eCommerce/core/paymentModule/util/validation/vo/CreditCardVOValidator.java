package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCardVO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.util.TextUtils;

import static ukma.eCommerce.util.validation.ValidationUtil.CARD_CVV;
import static ukma.eCommerce.util.validation.ValidationUtil.CARD_NUMBER;

/**
 * <p>
 * Custom validator for {@link CreditCardDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class CreditCardVOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CreditCardVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.card.vo.object", "Card instance wasn't passed");
            return;
        }

        final CreditCardDTO card = (CreditCardDTO) o;

        if (TextUtils.nullOrEmpty(card.getNumber())) {
            errors.rejectValue("number", "error.card.dto.number", "Credit card number wasn't specified");
        } else if (!CARD_NUMBER.matcher(card.getNumber()).matches()) {
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
        final DateTime now = DateTime.now();

        if (expDate == null) {
            errors.rejectValue("expirationDate", "error.card.dto.expirationDate",
                    "Credit card expire date wasn't specified or broken");
        } else if (now.compareTo(expDate) > 0) {
            // card is expired
            errors.rejectValue("expirationDate", "error.card.dto.expirationDate",
                    String.format("Credit card is expired, now is %s, but was - %s", now, expDate));
        }
    }
}
