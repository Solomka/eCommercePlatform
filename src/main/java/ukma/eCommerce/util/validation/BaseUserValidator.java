package ukma.eCommerce.util.validation;

import java.util.regex.Pattern;

import ukma.eCommerce.domain.bo.User;
import ukma.eCommerce.util.TextUtils;

/**
 * @author Ìàêñèì
 * */
//no one can instantiate this class outside of this package! 
class BaseUserValidator<T extends User> implements IValidator<T> {
	
	protected static final int minIdLen = 4, maxIdLen = 128;
	protected static final int minStrLen = 3, maxStrLen = 128;
	
	protected static final Pattern EMAIL_PATTERN = 
			Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	protected static final Pattern PLAIN_STR_PATTERN = 
			Pattern.compile(String.format("[a-zA-zà-ÿÀ-ß³²¯¿´¥ºªýÝúÚ]{%d,%d}", minStrLen, maxStrLen));// what about allowed string length?
	
	BaseUserValidator() {
	}

	@Override
	public ValidationResult validate(T user) {
		
		if(user == null)
			throw new NullPointerException("user == null");
		
		// email and id are required at least
		// another fields should be either empty or null or valid strings
		final String id = user.getId();
		
		if(id == null || id.length() < minIdLen || id.length() > maxIdLen) {
			return ValidationResult.createInvalidResult(String.format("User id '%s' is invalid, it should contain %d-%d characters", 
					user.getId(), minIdLen, maxIdLen));
		}
		
		if(TextUtils.nullOrEmpty(user.getEmail()) || 
				!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
			return ValidationResult.createInvalidResult(String.format("Email '%s' is invalid", user.getEmail()));
		}
		
		if(!TextUtils.nullOrEmpty(user.getName()) && 
				!PLAIN_STR_PATTERN.matcher(user.getName()).matches()) {
			return ValidationResult.createInvalidResult(String.format("Name '%s' is invalid", user.getName()));
		}
		
		if(!TextUtils.nullOrEmpty(user.getSurname()) && 
				!PLAIN_STR_PATTERN.matcher(user.getSurname()).matches()) {
			return ValidationResult.createInvalidResult(String.format("Surname '%s' is invalid", user.getSurname()));
		}
		
		if(!TextUtils.nullOrEmpty(user.getCountry()) && 
				!PLAIN_STR_PATTERN.matcher(user.getCountry()).matches()) {
			return ValidationResult.createInvalidResult(String.format("Country '%s' is invalid", user.getCountry()));
		}
		
		if(!TextUtils.nullOrEmpty(user.getAddress()) && 
				!PLAIN_STR_PATTERN.matcher(user.getAddress()).matches()) {
			return ValidationResult.createInvalidResult(String.format("Adress '%s' is invalid", user.getAddress()));
		}
		
		return ValidationResult.createValidResult();
	}

}
