package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Created by Максим on 10/20/2016.
 */
public final class ProdCharacteritics {

	@NotNull
	@Pattern(regexp = "^\\w{3,50}$")
	private final String name;

	@NotNull
	@Range(min = 100, max = 512)
	private final String description;

	@NotNull
	@Valid
	private final Type type;

	public static class Builder implements IBuilder<ProdCharacteritics> {

		private String name;
		private String description;
		private Type type;

		public Builder() {

		}

		public Builder(ProdCharacteritics prodCharacteritics) {
			Objects.requireNonNull(prodCharacteritics, "prodCharacteritics == null");

			setName(prodCharacteritics.getName()).setDescription(prodCharacteritics.getDescription())
					.setType(prodCharacteritics.getType());
		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public String getDescription() {
			return description;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Type getType() {
			return type;
		}

		public Builder setType(Type type) {
			this.type = type;
			return this;
		}

		@Override
		public ProdCharacteritics build() {
			return new ProdCharacteritics(this);
		}
	}

	public ProdCharacteritics(Builder builder) {

		Objects.requireNonNull(builder, "builder must no be null");

		this.name = ValidationUtil.validate(builder.getName());
		this.description = ValidationUtil.validate(builder.getDescription());
		this.type = ValidationUtil.validate(builder.getType());
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Type getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ProdCharacteritics other = (ProdCharacteritics) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdCharacteritics [name=" + name + ", description=" + description + ", type=" + type + "]";
	}

}
