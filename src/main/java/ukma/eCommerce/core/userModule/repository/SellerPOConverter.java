package ukma.eCommerce.core.userModule.repository;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.core.userModule.model.domain.dwo.SellerSaveDTO;
import ukma.eCommerce.core.userModule.repository.po.SellerPO;

/**
 * 
 * @author Solomka
 *
 */
final class SellerPOConverter {

	private SellerPOConverter() {
		throw new RuntimeException();
	}

	/**
	 * convert from SellerSaveDTO to SellerPO
	 * 
	 * @param sellerSaveDTO
	 * @return
	 */

	@NotNull
	static SellerPO fromSellerSaveDTO(@NotNull SellerSaveDTO sellerSaveDTO) {

		return new SellerPO();
	}

	/**
	 * convert from Seller to SellerPO
	 * 
	 * @param seller
	 * @return
	 */
	@NotNull
	static SellerPO fromSeller(@NotNull Seller seller) {
		return new SellerPO();
	}

	/**
	 * convert from SellerPO to Seller
	 * 
	 * @param SellerPO
	 * @return
	 */

	@NotNull
	static Seller toSeller(@NotNull SellerPO sellerPO) {
		return new Seller.Builder().build();
	}

}
