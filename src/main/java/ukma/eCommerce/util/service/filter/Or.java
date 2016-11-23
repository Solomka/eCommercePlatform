package ukma.eCommerce.util.service.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
class Or implements IExposedFilter {

	private IExposedFilter first;
	private IExposedFilter second;

	public Or(IExposedFilter first, IExposedFilter second) {
		this.first = first;
		this.second = second;
	}

	public IExposedFilter getFirst() {
		return first;
	}

	public Or setFirst(IExposedFilter first) {
		this.first = first;
		return this;
	}

	public IExposedFilter getSecond() {
		return second;
	}

	public Or setSecond(IExposedFilter second) {
		this.second = second;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Specifications<Object> toFilter() {
		return Specifications.where((Specification<Object>) first.toFilter())
				.or((Specification<Object>) second.toFilter());
	}
}
