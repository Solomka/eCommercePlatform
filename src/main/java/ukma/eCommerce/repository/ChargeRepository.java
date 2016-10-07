package ukma.eCommerce.repository;

import java.util.Collection;

import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.domain.bo.Payment;
import ukma.eCommerce.repository.filter.PaymentFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

public class ChargeRepository implements IRepository<Payment, String, ChargeVO, PaymentFilter> {

	@Override
	public void find(PaymentFilter filter, IRetrieveCallback<Collection<Payment>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(ChargeVO form, IRetrieveCallback<Payment> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ChargeVO form, String key, IRetrieveCallback<Payment> callback) {
		// TODO Auto-generated method stub

	}

}
