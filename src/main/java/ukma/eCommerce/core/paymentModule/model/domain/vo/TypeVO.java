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

	private final int typeId;
	@NotNull
	@Pattern(regexp = "[a-zA-z]{3,50}")
	private final String typeName;
	@NotNull
	@Valid
	private final CategoryVO category;

	public TypeVO(String typeName, CategoryVO category, int typeId) {
		
		this.typeName = Objects.requireNonNull(typeName, "typeName must not be null");
		this.category = Objects.requireNonNull(category, "category must not be null");
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public CategoryVO getCategory() {
		return category;
	}

	public int getTypeId() {
		return typeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TypeVO typeVO = (TypeVO) o;

		if (typeId != typeVO.typeId) return false;
		if (!typeName.equals(typeVO.typeName)) return false;
		return category.equals(typeVO.category);

	}

	@Override
	public int hashCode() {
		int result = typeId;
		result = 31 * result + typeName.hashCode();
		result = 31 * result + category.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "TypeVO{" +
				"typeId=" + typeId +
				", typeName='" + typeName + '\'' +
				", category=" + category +
				'}';
	}
}
