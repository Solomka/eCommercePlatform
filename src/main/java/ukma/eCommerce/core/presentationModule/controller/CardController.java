package ukma.eCommerce.core.presentationModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Максим on 10/15/2016.
 */
@Controller
@RequestMapping("/card")
final class CardController {
	/*
	 * private final CreditCardDTOValidator validator;
	 * 
	 * @Autowired public CardController(CreditCardDTOValidator validator) {
	 * this.validator = validator;
	 */
	/*
	 * ValidatorFactory validatorFactory =
	 * Validation.buildDefaultValidatorFactory();
	 * 
	 * javax.validation.Validator validator1 = validatorFactory.getValidator();
	 * 
	 * Set<ConstraintViolation<T>> set = validator1.validate(new
	 * T(DateTime.now().plusDays(10), "asddsadsadasdadad"));
	 * 
	 * System.out.println("Set "+set);
	 */
	/*
	 * }
	 * 
	 * @InitBinder private void initBinder(WebDataBinder binder) {
	 * binder.setValidator(validator);
	 * binder.registerCustomEditor(DateTime.class, "expirationDate", new
	 * JodaTimeEditor()); }
	 * 
	 * @RequestMapping(value = "/form", method = RequestMethod.GET) public
	 * String setupForm(Model model) {
	 * 
	 * final CreditCardDTO cardDTO = new CreditCardDTO();
	 * model.addAttribute("card", cardDTO); return "card"; }
	 * 
	 * @RequestMapping(value = "/check", method = RequestMethod.POST) public
	 * ModelAndView checkCard(@ModelAttribute("card") CreditCardDTO card,
	 * BindingResult result) {
	 * 
	 * ModelAndView model = new ModelAndView("card");
	 * 
	 * validator.validate(card, result);
	 * 
	 * if (result.hasErrors()) { model.addObject("errors",
	 * result.getAllErrors().toString()); } else { model.addObject("errors",
	 * "none"); }
	 * 
	 * return model; }
	 */

}
