package ukma.eCommerce.core.paymentModule.repository;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Product;
import ukma.eCommerce.core.paymentModule.model.dwo.ProductSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.ProductPO;

/**
 * 
 * @author Solomka
 *
 */
final class ProductPOConverter {

	private ProductPOConverter() {
		throw new RuntimeException();
	}

	/**
	 * convert from ProductSaveDTO to ProductPO
	 * 
	 * @param productSaveDTO
	 * @return
	 */

	@NotNull
	static ProductPO fromProductSaveDTO(@NotNull ProductSaveDTO productSaveDTO) {

		return new ProductPO();
	}

	/**
	 * convert from Product to ProductPO
	 * 
	 * @param product
	 * @return
	 */
	@NotNull
	static ProductPO fromProduct(@NotNull Product product) {
		return new ProductPO();
	}

	/**
	 * convert from ProductPO to Product
	 * 
	 * @param ProductPO
	 * @return
	 */

	@NotNull
	static Product toProduct(@NotNull ProductPO productPO) {
		return new Product.Builder().build();
	}

}
