package ukma.eCommerce.core.paymentModule.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.core.paymentModule.util.JodaTimeEditor;
import ukma.eCommerce.core.paymentModule.util.validation.dto.CreditCardDTOValidator;

/**
 * Created by Максим on 10/15/2016.
 */
@Controller
@RequestMapping("/card")
final class CardController {

    private final CreditCardDTOValidator validator;

    @Autowired
    public CardController(CreditCardDTOValidator validator) {
        this.validator = validator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
        binder.registerCustomEditor(DateTime.class, "expirationDate", new JodaTimeEditor());
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String setupForm(Model model) {

        final CreditCardDTO cardDTO = new CreditCardDTO();
        model.addAttribute("card", cardDTO);
        return "card";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView checkCard(@ModelAttribute("card") CreditCardDTO card, BindingResult result) {

        ModelAndView model = new ModelAndView("card");

        validator.validate(card, result);

        if (result.hasErrors()) {
            model.addObject("errors", result.getAllErrors().toString());
        } else {
            model.addObject("errors", "none");
        }

        return model;
    }

}
