package ukma.eCommerce.core.paymentModule.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * Created by Максим on 11/6/2016.
 */
@Service
public final class OrderApplicationService implements IOrderApplicationService {

	private static final Logger LOGGER = Logger.getLogger(OrderApplicationService.class.getName());

	private final IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> repository;

	@Autowired
	public OrderApplicationService(@NotNull IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> repository) {
		this.repository = repository;
	}

	@Override
	public Observable<OrderDTO> createOrder(OrderDTO dto) {

		if (!dto.getClass().isAnnotationPresent(Validated.class))
			throw new RuntimeException("@Validate annotation expected!");

		return Observable.create(subscriber ->
		// convert dto into entity instance and
		// try to create order in repository asynchronously
		repository.create(OrderDtoConverter.toEntity(dto))
				// handle repository result
				.subscribe(order -> subscriber.onNext(OrderDtoConverter.toDto(order)), th -> {
					LOGGER.log(Level.WARNING, String.format("Error occurred while creating order for dto %s", dto), th);
					subscriber.onError(new Exception("Failed to create order"));
				}));
	}

}
