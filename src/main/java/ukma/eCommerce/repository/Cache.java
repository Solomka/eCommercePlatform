package ukma.eCommerce.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

public class Cache  implements IRepository<Card, String, CreditCardVO, CardFilter> {
	
	private Collection<Card> cardsCache = new ArrayList<>();

	@Override
	public void find(CardFilter filter, IRetrieveCallback<Collection<Card>> callback) {
		
		callback.onPreExecute();
		
		List<Card> result = cardsCache.stream().filter(card -> 
	
		// some filtering predicate
		filter.getExpDate() == null ? true : card.getExpirationDate().equals(filter.getExpDate())
				&& filter.getMinMoneyAmount() > 0 ? card.getMoney() >= filter.getMinMoneyAmount() : true
				&& filter.getMaxMoneyAmount() > 0 ? card.getMoney() <= filter.getMaxMoneyAmount() : true
				
		).collect(Collectors.toList());
		
		callback.onResult(result);
	}

	@Override
	public void create(CreditCardVO form, IRetrieveCallback<Card> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CreditCardVO form, String key, IRetrieveCallback<Card> callback) {
		// TODO Auto-generated method stub
		
	}
	
	

}
