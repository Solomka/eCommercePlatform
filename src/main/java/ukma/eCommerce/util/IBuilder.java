package ukma.eCommerce.util;

import javax.validation.constraints.NotNull;

public interface IBuilder <T> {
	
	@NotNull T build();

}
