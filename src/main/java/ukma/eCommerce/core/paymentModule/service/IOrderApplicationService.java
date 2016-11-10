package ukma.eCommerce.core.paymentModule.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.dwo.InOrderDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OutOrderDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Application service that handles order operations. Only this
 * service can be used by other services.
 */
@Validated
@Service
public interface IOrderApplicationService {

    /**
     * Creates order and returns operation result asynchronously
     *
     * @param orderDTO dto from which order should be created
     *                 (should be valid or exception will be thrown)
     * @return observable to monitor operation state
     */
    @NotNull
    Observable<OutOrderDTO> createOrder(@NotNull(message = "null value isn't allowed") @Valid InOrderDTO orderDTO);

}
