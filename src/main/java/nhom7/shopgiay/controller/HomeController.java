package nhom7.shopgiay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {"/", "/home"})
	public String homePage(Model model) {
		String page = "client_index";
		model.addAttribute("page", page);
		return "client_main-frame";
	}

	
}
