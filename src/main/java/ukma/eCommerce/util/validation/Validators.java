package ukma.eCommerce.util.validation;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Customer;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Seller;
import ukma.eCommerce.core.paymentModule.model.domain.bo.User;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An utility class to conveniently manage application validators
 * @author ������
 * */
public final class Validators {
	
	private static final Map<Class<? extends IValidateable>, IValidator<? extends IValidateable>> bindings;
	
	static {
		bindings = new HashMap<>();
		registerValidators();
	}
	
	private Validators() {
		throw new IllegalAccessError("Shouldn't be called");
	}
	
	/**
	 * Returns validator by class
	 * @param <T> validate target class which implements {@linkplain IValidateable}
	 * @param c class for which validator should be returned
	 * */
	@SuppressWarnings("unchecked")
	public static <T extends IValidateable> IValidator<T> forClass(@NotNull Class<T> c) {
		
		final IValidator<T> validator = 
				(IValidator<T>) bindings.get(Objects.requireNonNull(c, "class == null"));
		
		if(validator == null)
			throw new RuntimeException(String.format("unregistered validator for class %s", c.getName()));
		
		return validator;
	}
	
	private static void registerValidators() {
		// register your validators here
		bindings.put(User.class, new BaseUserValidator<>());
		bindings.put(Seller.class, new SellerValidator());
		bindings.put(Customer.class, new CustomerValidator());
	}

}
