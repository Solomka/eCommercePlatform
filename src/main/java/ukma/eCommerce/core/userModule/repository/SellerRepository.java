package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.core.userModule.model.domain.dwo.SellerEntity;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.core.userModule.repository.po.SellerPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Repository("sellerRepository")
public class SellerRepository extends
		AHibernateRepository<ukma.eCommerce.core.userModule.model.domain.bo.Seller, SellerID, SellerEntity, IExposedFilter, SellerPO, UUID> {

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
	public Observable<Boolean> delete(@NotNull SellerID sellerID) {
		return null;
	}

	@Override
	public Observable<Seller> update(@NotNull Seller seller) {
		return null;
	}

}
