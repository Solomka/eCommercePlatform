package ukma.eCommerce.core.presentationModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.portlet.ModelAndView;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.paymentModule.service.IOrderApplicationService;

import javax.validation.Valid;

/**
 * Created by Максим on 10/8/2016.
 */
@Controller
final class OrderController {

    private final IOrderApplicationService orderService;

    @Autowired
    OrderController(IOrderApplicationService orderService) {
        this.orderService = orderService;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        //binder.registerCustomEditor(DateTime.class, "expirationDate", new JodaTimeEditor());
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String preparePage(Model model) {
        model.addAttribute("order", new OrderDTO());
        return "order";
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ModelAndView createOrder(@Valid @ModelAttribute("order") OrderDTO dto, BindingResult bindingResult) {

        ModelAndView model = new ModelAndView("order");

        if (bindingResult.hasErrors()) {
            model.addObject("errors", bindingResult.getAllErrors().toString());
            return model;
        }

        return model.addObject("errors", "none");
    }

    @RequestMapping(value = "/order/create1", method = RequestMethod.POST)
    public DeferredResult<OrderDTO> createOrder1(@Valid @ModelAttribute("order") OrderDTO dto, BindingResult bindingResult) {

        final DeferredResult<OrderDTO> deferred = new DeferredResult<>(5_000L);

        if (bindingResult.hasErrors()) {
            deferred.setErrorResult(bindingResult);
            return deferred;
        }
        orderService.createOrder(dto).subscribe(deferred::setResult, deferred::setErrorResult);
        return deferred;
    }

}
