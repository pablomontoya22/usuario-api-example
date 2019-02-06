package app.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainWebController {
	@RequestMapping("/")
	public String index(ModelMap model) {
		return "index";
	}
}
