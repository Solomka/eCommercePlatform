package ukma.eCommerce.repository;

import java.util.Collection;

import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.domain.bo.Charge;
import ukma.eCommerce.repository.filter.ChargeFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

public class ChargeRepository implements IRepository<Charge, String, ChargeVO, ChargeFilter> {

	@Override
	public void find(ChargeFilter filter, IRetrieveCallback<Collection<Charge>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(ChargeVO form, IRetrieveCallback<Charge> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ChargeVO form, String key, IRetrieveCallback<Charge> callback) {
		// TODO Auto-generated method stub

	}

}
