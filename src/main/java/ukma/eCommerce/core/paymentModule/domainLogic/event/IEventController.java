package ukma.eCommerce.core.paymentModule.domainLogic.event;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/17/2016.
 */
@Validated
public interface IEventController {

    void dispatch(@NotNull Event event);

}
