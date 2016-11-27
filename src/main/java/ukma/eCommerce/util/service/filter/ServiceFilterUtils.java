package ukma.eCommerce.util.service.filter;

import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * service_specific filters for combining filters 
 * 
 * @author Solomka
 *
 */
public final class ServiceFilterUtils {

	private ServiceFilterUtils() {
		throw new RuntimeException();
	}
	
	/**
	 * And filter
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static IExposedFilter and(IExposedFilter first, IExposedFilter second) {
		return new And(first, second);
	}

	/**
	 * Or filter
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static IExposedFilter or(IExposedFilter first, IExposedFilter second) {
		return new Or(first, second);
	}

	/**
	 * Not filter
	 * 
	 * @param value
	 * @return
	 */
	public static IExposedFilter not(IExposedFilter value) {
		return new Not(value);
	}

}
