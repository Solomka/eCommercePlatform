package ukma.eCommerce.core.userModule.repository;

import org.springframework.stereotype.Repository;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.core.userModule.model.domain.dwo.SellerEntity;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.core.userModule.repository.po.SellerPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;
/**
 * 
 * @author Solomka
 *
 */
@Repository("sellerRepository")
public class SellerRepository extends AHibernateRepository<SellerPO, UUID> implements
		IRepository<ukma.eCommerce.core.userModule.model.domain.bo.Seller, SellerID, SellerEntity, IExposedFilter> {


	@Override
	public Collection<Seller> find(IExposedFilter iExposedFilter) {
		return null;
	}

	@Override
	public Seller create(@NotNull SellerEntity sellerEntity) {
		return null;
	}

	@Override
	public void delete(@NotNull SellerID sellerID) {

	}

	@Override
	public Seller update(@NotNull Seller seller) {
		return null;
	}
}
