package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.AddressVO;
import ukma.eCommerce.core.userModule.validation.vo.CustomerVOValidator;
import ukma.eCommerce.util.validation.ValidationUtil;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by Максим on 10/17/2016.
 */
@Component
public final class AddressVOValidator implements Validator {

    @Value("${countryMinLen}") private int countryMinLen;
    @Value("${countryMaxLen}") private int countryMaxLen;
    @Value("${stateMinLen}") private int stateMinLen;
    @Value("${stateMaxLen}") private int stateMaxLen;
    @Value("${regionMinLen}") private int regionMinLen;
    @Value("${regionMaxLen}") private int regionMaxLen;
    @Value("${cityMinLen}") private int cityMinLen;
    @Value("${cityMaxLen}") private int cityMaxLen;
    @Value("${streetMinLen}") private int streetMinLen;
    @Value("${streetMaxLen}") private int streetMaxLen;
    @Value("${postMinLen}") private int postMinLen;
    @Value("${postMaxLen}") private int postMaxLen;

    private Pattern countryPattern;
    private Pattern statePattern;
    private Pattern regionPattern;
    private Pattern cityPattern;
    private Pattern streetPattern;
    private Pattern postCodePattern;

    private final FullNameVOValidator fullNameVOValidator;
    private final CustomerVOValidator customerVOValidator;

    @Autowired
    public AddressVOValidator(FullNameVOValidator fullNameVOValidator, CustomerVOValidator customerVOValidator) {
        this.fullNameVOValidator = Objects.requireNonNull(fullNameVOValidator);
        this.customerVOValidator = Objects.requireNonNull(customerVOValidator);
    }

    @PostConstruct
    private void init() {
        countryPattern = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, countryMinLen, countryMaxLen));
        statePattern = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, stateMinLen, stateMaxLen));
        regionPattern = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, regionMinLen, regionMaxLen));
        cityPattern = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, cityMinLen, cityMaxLen));
        streetPattern = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, streetMinLen, streetMaxLen));
        postCodePattern = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, postMinLen, postMaxLen));
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

        if(address.getCountry() == null || !countryPattern.matcher(address.getCountry()).matches()) {
            errors.rejectValue("country", "error.address.vo.country", "country isn't valid");
        }

        if(address.getState() != null && !statePattern.matcher(address.getState()).matches()) {
            errors.rejectValue("state", "error.address.vo.state", "state isn't valid");
        }

        if(address.getRegion() != null && !regionPattern.matcher(address.getRegion()).matches()) {
            errors.rejectValue("region", "error.address.vo.region", "region isn't valid");
        }

        if(address.getCity() == null || !cityPattern.matcher(address.getCity()).matches()) {
            errors.rejectValue("city", "error.address.vo.city", "city isn't valid");
        }

        if(address.getStreet() == null || !streetPattern.matcher(address.getStreet()).matches()) {
            errors.rejectValue("street", "error.address.vo.street", "street isn't valid");
        }

        if(address.getPostCode() == null || !postCodePattern.matcher(address.getPostCode()).matches()) {
            errors.rejectValue("postCode", "error.address.vo.postCode", "postCode isn't valid");
        }

        /*if(address.getPhone() == null || !PHONE_PATTERN.matcher(address.getPhone()).matches()) {
            errors.rejectValue("phone", "error.address.vo.phone", "phone isn't valid");
        }*/
    }
}
