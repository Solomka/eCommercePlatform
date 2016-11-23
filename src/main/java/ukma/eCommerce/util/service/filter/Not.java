package ukma.eCommerce.util.service.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
class Not implements IExposedFilter {

	private IExposedFilter value;

	public Not(IExposedFilter value) {
		this.value = value;
	}
	
	

	public IExposedFilter getValue() {
		return value;
	}



	public Not setValue(IExposedFilter value) {
		this.value = value;
		return this;
	}



	@SuppressWarnings("unchecked")
	@Override
	public Specifications<Object> toFilter() {
		return Specifications.not((Specification<Object>) value.toFilter());
	}
}
