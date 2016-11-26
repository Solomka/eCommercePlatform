package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class SellerRepository extends
		AHibernateRepository<ukma.eCommerce.core.userModule.model.domain.bo.Seller, SellerID, SellerEntity, IExposedFilter, SellerPO, UUID> {

	@Override
	public Collection<Seller> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seller create(SellerEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SellerID k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller update(Seller t) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
