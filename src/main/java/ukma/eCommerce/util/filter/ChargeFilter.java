package ukma.eCommerce.util.filter;

import org.joda.time.Period;

public class ChargeFilter extends BasicFilter {

	private int minPrice;
	
	private int maxPrice;
	
	private Period period;

	public final int getMinPrice() {
		return minPrice;
	}

	public final ChargeFilter setMinPrice(int minPrice) {
		this.minPrice = minPrice;
		return this;
	}

	public final int getMaxPrice() {
		return maxPrice;
	}

	public final ChargeFilter setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
		return this;
	}

	public final Period getPeriod() {
		return period;
	}

	public final ChargeFilter setPeriod(Period period) {
		this.period = period;
		return this;
	}
	
}
