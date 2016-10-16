package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.AddressVO;
import ukma.eCommerce.core.userModule.validation.vo.CustomerVOValidator;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.util.Objects;
import java.util.regex.Pattern;

import static ukma.eCommerce.util.validation.ValidationUtil.PHONE_PATTERN;

/**
 * Created by Максим on 10/17/2016.
 */
@Component
public final class AddressVOValidator implements Validator {

    private final Pattern COUNTRY_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private final Pattern STATE_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private final Pattern REGION_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private final Pattern CITY_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private final Pattern STREET_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private final Pattern POST_CODE_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));

    private final FullNameVOValidator fullNameVOValidator;
    private final CustomerVOValidator customerVOValidator;

    @Autowired
    public AddressVOValidator(FullNameVOValidator fullNameVOValidator, CustomerVOValidator customerVOValidator) {
        this.fullNameVOValidator = Objects.requireNonNull(fullNameVOValidator);
        this.customerVOValidator = Objects.requireNonNull(customerVOValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.shipment.vo.object", "Shipment instance wasn't passed");
            return;
        }

        final AddressVO address = (AddressVO) o;

        if(fullNameVOValidator.supports(Objects.requireNonNull(address.getFullName()).getClass()))
            throw new RuntimeException();

        if(customerVOValidator.supports(Objects.requireNonNull(address.getCustomer()).getClass()))
            throw new RuntimeException();

        fullNameVOValidator.validate(address.getFullName(), errors);
        customerVOValidator.validate(address.getCustomer(), errors);

        if(address.getCountry() == null || !COUNTRY_PATTERN.matcher(address.getCountry()).matches()) {
            errors.rejectValue("country", "error.address.vo.country", "country isn't valid");
        }

        if(address.getState() == null || !STATE_PATTERN.matcher(address.getState()).matches()) {
            errors.rejectValue("state", "error.address.vo.state", "state isn't valid");
        }

        if(address.getRegion() == null || !REGION_PATTERN.matcher(address.getRegion()).matches()) {
            errors.rejectValue("region", "error.address.vo.region", "region isn't valid");
        }

        if(address.getCity() == null || !CITY_PATTERN.matcher(address.getCity()).matches()) {
            errors.rejectValue("city", "error.address.vo.city", "city isn't valid");
        }

        if(address.getStreet() == null || !STREET_PATTERN.matcher(address.getStreet()).matches()) {
            errors.rejectValue("street", "error.address.vo.street", "street isn't valid");
        }

        if(address.getPostCode() == null || !POST_CODE_PATTERN.matcher(address.getPostCode()).matches()) {
            errors.rejectValue("postCode", "error.address.vo.postCode", "postCode isn't valid");
        }

        if(address.getPhone() == null || !PHONE_PATTERN.matcher(address.getPhone()).matches()) {
            errors.rejectValue("phone", "error.address.vo.phone", "phone isn't valid");
        }
    }
}
