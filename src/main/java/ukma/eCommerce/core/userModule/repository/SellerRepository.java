package ukma.eCommerce.core.userModule.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.core.userModule.model.domain.dwo.SellerSaveDTO;
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
		AHibernateRepository<ukma.eCommerce.core.userModule.model.domain.bo.Seller, SellerID, SellerSaveDTO, IExposedFilter, SellerPO, UUID> {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Seller> find(IExposedFilter f) {
		return findAllBySpecification((Specification<SellerPO>) f.toFilter()).stream()
				.map(sellerPO -> SellerPOConverter.toSeller(sellerPO)).collect(Collectors.toList());
	}

	@Override
	public Seller create(SellerSaveDTO sellerSaveDTO) {
		final SellerPO sellerPO = SellerPOConverter.fromSellerSaveDTO(sellerSaveDTO);
		savePO(sellerPO);
		return SellerPOConverter.toSeller(sellerPO);
	}

	@Override
	public Seller update(Seller seller) {
		final SellerPO sellerPO = updatePO(SellerPOConverter.fromSeller(seller));
		return SellerPOConverter.toSeller(sellerPO);
	}

	@Override
	public void delete(SellerID sellerId) {
		deletePOById(sellerId.getId());
	}

}
