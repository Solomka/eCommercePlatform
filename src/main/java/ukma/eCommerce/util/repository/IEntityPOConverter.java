package ukma.eCommerce.util.repository;

import javax.validation.constraints.NotNull;

public abstract class IEntityPOConverter<SaveDTO, AR, PO> {

	/**
	 * convert from SaveDTO to PO
	 * 
	 * @param entity
	 * @return
	 */
	@NotNull
	public abstract PO fromEntity(@NotNull SaveDTO dto);

	/**
	 * convert from Aggregation root to PO
	 * 
	 * @param arr
	 * @return
	 */

	@NotNull
	public abstract PO fromAR(@NotNull AR arr);

	/**
	 * convert from PO to AR
	 * 
	 * @param orderPO
	 * @return
	 */
	@NotNull
	public abstract AR toARoot(@NotNull PO po);

}
