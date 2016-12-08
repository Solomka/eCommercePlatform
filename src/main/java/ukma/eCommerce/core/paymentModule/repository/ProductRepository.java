package ukma.eCommerce.core.paymentModule.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Product;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.paymentModule.model.dwo.ProductSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.ProductPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Repository("productRepository")
@Transactional
public class ProductRepository
		extends AHibernateRepository<Product, ProductID, ProductSaveDTO, IExposedFilter, ProductPO, UUID> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> find(IExposedFilter f) {
		return findAllBySpecification((Specification<ProductPO>) f.toFilter()).stream()
				.map(productPO -> ProductPOConverter.toProduct(productPO)).collect(Collectors.toList());
	}

	@Override
	public Product create(ProductSaveDTO productSaveDTO) {
		final ProductPO productPO = ProductPOConverter.fromProductSaveDTO(productSaveDTO);
		savePO(productPO);
		return ProductPOConverter.toProduct(productPO);
	}

	@Override
	public Product update(Product product) {
		final ProductPO productPO = updatePO(ProductPOConverter.fromProduct(product));
		return ProductPOConverter.toProduct(productPO);
	}

	@Override
	public void delete(ProductID productId) {
		deletePOById(productId.getId());
	}

}
