package ukma.eCommerce.repository;

import java.util.Collection;

import ukma.eCommerce.controller.vo.CardForm;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

// dumb example of database repository
public class DBCardRepository implements IRepository<Card, String, CardForm, CardFilter> {

	@Override
	public void find(CardFilter filter, IRetrieveCallback<Collection<Card>> callback) {
		// TODO Auto-generated method stub
		toSql(filter);// process sql query
	}

	@Override
	public void create(CardForm form, IRetrieveCallback<Card> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CardForm form, String key, IRetrieveCallback<Card> callback) {
		// TODO Auto-generated method stub
		
	}
	
	private String toSql(CardFilter filter) {
		
		// just example
		String sql = "SELECT * FROM `card`";
		
		String where = null;
		
		if(filter.getNumber() != null) {
			where += " `cardNumber`=" + filter.getNumber();
		}
		
		if(filter.getMaxMoneyAmount() > 0 && filter.getMinMoneyAmount() > 0) {
			where += " `money`<=" + filter.getMaxMoneyAmount() + " AND `money` >= " + filter.getMinMoneyAmount();
		}
		
		if(where != null) {
			sql += where;
		}
		
		return sql;
	}


}
