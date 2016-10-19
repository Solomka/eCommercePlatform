package ukma.eCommerce.core.paymentModule.model.domain.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * value object that represents Type of Product
 * 
 * @author Solomka
 *
 */
public final class TypeVO {

	@NotNull
	@Pattern(regexp = "[a-zA-z]{3,50}")
	private final String typeName;
	@NotNull
	@Valid
	private final CategoryVO category;

	public TypeVO(String typeName, CategoryVO category) {

		this.typeName = Objects.requireNonNull(typeName, "typeName must not be null");
		this.category = Objects.requireNonNull(category, "category must not be null");
	}

	public String getTypeName() {
		return typeName;
	}

	public CategoryVO getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		TypeVO other = (TypeVO) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TypeVO [typeName=" + typeName + ", category=" + category + "]";
	}

}
