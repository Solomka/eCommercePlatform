package ukma.eCommerce.util.filter;

import org.joda.time.DateTime;

public class CardFilter extends BasicFilter {

	private String number;
	private DateTime expDate;
	private int minMoneyAmount = -1;
	private int maxMoneyAmount = -1;

	public DateTime getExpDate() {
		return expDate;
	}

	public CardFilter setExpDate(DateTime expDate) {
		this.expDate = expDate;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public CardFilter setNumber(String number) {
		this.number = number;
		return this;
	}

	public int getMinMoneyAmount() {
		return minMoneyAmount;
	}

	public CardFilter setMinMoneyAmount(int moneyAmount) {
		this.minMoneyAmount = moneyAmount;
		return this;
	}

	public int getMaxMoneyAmount() {
		return maxMoneyAmount;
	}

	public CardFilter setMaxMoneyAmount(int maxMoneyAmount) {
		this.maxMoneyAmount = maxMoneyAmount;
		return this;
	}
	
	
}
