package ukma.eCommerce.controller.vo;

public class CardsForm {
	
	private int minMoneyAmount = -1;
	private int maxMoneyAmount = -1;

	public int getMinMoneyAmount() {
		return minMoneyAmount;
	}

	public void setMinMoneyAmount(int moneyAmount) {
		this.minMoneyAmount = moneyAmount;
	}

	public int getMaxMoneyAmount() {
		return maxMoneyAmount;
	}

	public void setMaxMoneyAmount(int maxMoneyAmount) {
		this.maxMoneyAmount = maxMoneyAmount;
	}
	
	

}
