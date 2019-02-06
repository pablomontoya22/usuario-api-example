package app.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientWebController {

	@GetMapping()
	public ModelAndView list(ModelMap model) {
		return new ModelAndView("client/list");
	}

}
