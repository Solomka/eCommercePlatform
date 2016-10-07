package ukma.eCommerce.repository.filter;

import org.joda.time.Period;

public class PaymentFilter extends BasicFilter {

	private int minPrice;
	
	private int maxPrice;
	
	private Period period;

	public final int getMinPrice() {
		return minPrice;
	}

	public final PaymentFilter setMinPrice(int minPrice) {
		this.minPrice = minPrice;
		return this;
	}

	public final int getMaxPrice() {
		return maxPrice;
	}

	public final PaymentFilter setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
		return this;
	}

	public final Period getPeriod() {
		return period;
	}

	public final PaymentFilter setPeriod(Period period) {
		this.period = period;
		return this;
	}
	
}
