package app.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.controllers.api.ClientController;

@Controller
@RequestMapping("/client")
public class ClientWebController extends ClientController {

	@GetMapping()
	public ModelAndView list(ModelMap model) {
		model.addAttribute("clients", r.findAll());
		return new ModelAndView("client/list");
	}

}
