package ukma.eCommerce.core.presentationModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeExecutionDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.core.paymentModule.service.IChargeApplicationService;

/**
 * @author Solomka
 */
@Controller
final class ChargeController {

    private IChargeApplicationService chargeService;

    @Autowired
    ChargeController(IChargeApplicationService chargeService) {
        this.chargeService = chargeService;
    }

    // create charge
    @RequestMapping(value = "/createCharge", method = RequestMethod.POST)
    public DeferredResult<ChargeExecutionDTO> createCharge(@AuthenticationPrincipal User user,
                                                           @ModelAttribute("charge") ChargeDTO chargeDTO, @ModelAttribute("creditCard") CreditCardDTO creditCardDTO,
                                                           BindingResult bindingResult) {
        final DeferredResult<ChargeExecutionDTO> deferred = new DeferredResult<>(5_000L);

        if (bindingResult.hasErrors()) {
            deferred.setErrorResult(bindingResult);
            return deferred;
        }
        chargeService.createCharge(creditCardDTO, chargeDTO).subscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
    }

}
