package ukma.eCommerce.domain.bo;

import org.joda.time.DateTime;

public class Card {

	private final String number;
	private final String cvv;
	private final DateTime expirationDate;
	private final int money;
	
	
	public Card(String number, String cvv, DateTime expirationDate, int money) {
		super();
		this.number = number;
		this.cvv = cvv;
		this.expirationDate = expirationDate;
		this.money = money;
	}
	public String getNumber() {
		return number;
	}
	public String getCvv() {
		return cvv;
	}
	public DateTime getExpirationDate() {
		return expirationDate;
	}
	public int getMoney() {
		return money;
	}
	
	
	
}
