package ukma.eCommerce.util.repository.filter;
/**
 * Base class for repository filters. This class can be
 * easily extends to suite your needs
 * @author Max
 * */
public class BasicFilter {
	
	private int maxItems = -1;
	
	private Ordering ordering;
	
	public BasicFilter() {}

	public final int getMaxItems() {
		return maxItems;
	}

	public final BasicFilter setMaxItems(int maxItems) {
		this.maxItems = maxItems;
		return this;
	}

	public final Ordering getOrdering() {
		return ordering;
	}

	public final BasicFilter setOrdering(Ordering ordering) {
		this.ordering = ordering;
		return this;
	}
	
}