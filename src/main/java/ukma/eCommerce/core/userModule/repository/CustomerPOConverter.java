package ukma.eCommerce.core.userModule.repository;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.userModule.model.domain.bo.Customer;
import ukma.eCommerce.core.userModule.model.domain.dwo.CustomerSaveDTO;
import ukma.eCommerce.core.userModule.repository.po.Credentials;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;

/**
 * 
 * @author Solomka
 *
 */
final class CustomerPOConverter {

	private CustomerPOConverter() {
		throw new RuntimeException();
	}

	private static Credentials generatePOCredentials(
			ukma.eCommerce.core.userModule.model.domain.vo.Credentials credentialsVO) {
		return new Credentials(credentialsVO.getEmail(), credentialsVO.getLogin(), credentialsVO.getPassword(),
				credentialsVO.getPhone());
	}

	private static FullName generatePOFullName(ukma.eCommerce.core.userModule.model.domain.vo.FullName fullNameVO) {
		return new FullName(fullNameVO.getFirstName(), fullNameVO.getLastName());

	}

	/**
	 * convert from CustomerSaveDTO to CuustomerPO
	 * 
	 * @param customerSaveDTO
	 * @return
	 */

	@NotNull
	static CustomerPO fromCustomerSaveDTO(@NotNull CustomerSaveDTO customerSaveDTO) {

		return new CustomerPO(generatePOCredentials(customerSaveDTO.getCredentials()),
				generatePOFullName(customerSaveDTO.getFullName()));
	}

	/**
	 * convert from Customer to CustomerPO
	 * 
	 * @param customer
	 * @return
	 */
	@NotNull
	static CustomerPO fromCustomer(@NotNull Customer customer) {
		return new CustomerPO(generatePOCredentials(customer.getCredentials()),
				generatePOFullName(customer.getFullName()));
	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.Credentials generateVOCredentials(
			Credentials credentials) {
		// TODO Auto-generated method stub
		return new ukma.eCommerce.core.userModule.model.domain.vo.Credentials(credentials.getEmail(),
				credentials.getLogin(), credentials.getPassword(), credentials.getPhone());
	}

	private static ukma.eCommerce.core.userModule.model.domain.vo.FullName generateVOFullName(FullName fullName) {
		return new ukma.eCommerce.core.userModule.model.domain.vo.FullName(fullName.getFirstName(),
				fullName.getLastName());
	}

	/**
	 * convert from CustomerPO to Customer
	 * 
	 * @param customerPO
	 * @return
	 */

	@NotNull
	static Customer toCustomer(@NotNull CustomerPO customerPO) {
		return new Customer.Builder().setCredentials(generateVOCredentials(customerPO.getCredentials()))
				.setFullName(generateVOFullName(customerPO.getFullName())).build();
	}

}
