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

public final class CategoryVO {

	private final int id;
	@NotNull
	@Pattern(regexp = "[a-zA-z]{3,50}")
	private final String name;

	public CategoryVO(String name, int id) {
		this.name = Objects.requireNonNull(name, "name must not be null");
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CategoryVO that = (CategoryVO) o;

		if (id != that.id) return false;
		return name.equals(that.name);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "CategoryVO{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
