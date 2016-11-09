package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.core.userModule.model.domain.dwo.SellerEntity;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.core.userModule.repository.po.SellerPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

@Repository("sellerRepository")
public class SellerRepository extends AHibernateRepository<SellerPO, UUID> implements
		IRepository<ukma.eCommerce.core.userModule.model.domain.bo.Seller, SellerID, SellerEntity, IExposedFilter> {

	@Override
	public Observable<Collection<Seller>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Seller> create(SellerEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Void> delete(SellerID k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Seller> update(Seller t) {
		// TODO Auto-generated method stub
		return null;
	}

}
