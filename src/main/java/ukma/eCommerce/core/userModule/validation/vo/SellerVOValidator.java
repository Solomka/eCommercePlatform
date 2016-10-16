package ukma.eCommerce.core.userModule.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerVO;
import ukma.eCommerce.util.TextUtils;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.util.regex.Pattern;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class SellerVOValidator implements Validator {

    private static final Pattern BUSINESS_NAME_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 5, 128));
    private static final Pattern COUNTRY_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private static final Pattern CITY_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private static final Pattern STREET_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));
    private static final Pattern INDEX_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 64));

    private final UserVOValidator userValidator;

    @Autowired
    public SellerVOValidator(UserVOValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SellerVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.seller.vo.object", "Seller instance wasn't passed");
            return;
        }

        final SellerVO seller = (SellerVO) o;

        if (!userValidator.supports(seller.getClass()))
            throw new RuntimeException();

        userValidator.validate(seller, errors);

        if(TextUtils.nullOrEmpty(seller.getBusinessName())
                || !BUSINESS_NAME_PATTERN.matcher(seller.getBusinessName()).matches()) {
            errors.rejectValue("businessName", "seller.customer.vo.businessName",
                    String.format("Business name '%s' is invalid", seller.getBusinessName()));
        }

        if(!TextUtils.nullOrEmpty(seller.getCountry()) && !COUNTRY_PATTERN.matcher(seller.getCountry()).matches()) {
            errors.rejectValue("country", "seller.customer.vo.country",
                    String.format("Country '%s' is invalid", seller.getCountry()));
        }

        if(!TextUtils.nullOrEmpty(seller.getCity()) && !CITY_PATTERN.matcher(seller.getCity()).matches()) {
            errors.rejectValue("city", "seller.customer.vo.city",
                    String.format("City '%s' is invalid", seller.getCity()));
        }

        if(!TextUtils.nullOrEmpty(seller.getStreet()) && !STREET_PATTERN.matcher(seller.getStreet()).matches()) {
            errors.rejectValue("street", "seller.customer.vo.street",
                    String.format("Street '%s' is invalid", seller.getStreet()));
        }

        if(!TextUtils.nullOrEmpty(seller.getIndex()) && !INDEX_PATTERN.matcher(seller.getIndex()).matches()) {
            errors.rejectValue("index", "seller.customer.vo.index",
                    String.format("Index '%s' is invalid", seller.getIndex()));
        }
    }

}
