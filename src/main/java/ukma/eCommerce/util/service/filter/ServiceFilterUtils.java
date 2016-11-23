package ukma.eCommerce.util.service.filter;

import ukma.eCommerce.util.repository.filter.IExposedFilter;

public class ServiceFilterUtils {

	public static IExposedFilter and(IExposedFilter first, IExposedFilter second) {
		return new And(first, second);
	}

	public static IExposedFilter or(IExposedFilter first, IExposedFilter second) {
		return new Or(first, second);
	}

	public static IExposedFilter not(IExposedFilter value) {
		return new Not(value);
	}

}
