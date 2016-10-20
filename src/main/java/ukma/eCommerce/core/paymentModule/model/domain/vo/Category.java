package ukma.eCommerce.core.paymentModule.model.domain.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * value object that represents Category of Product
 * 
 * @author Solomka
 *
 */

public final class Category {

	@NotNull
	@Pattern(regexp = "[a-zA-z]{3,50}")
	private final String name;

	public Category(String name) {
		this.name = Objects.requireNonNull(name, "name must not be null");
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + "]";
	}

}
