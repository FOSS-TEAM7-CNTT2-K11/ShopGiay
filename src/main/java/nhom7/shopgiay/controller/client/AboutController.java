package nhom7.shopgiay.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	
	@GetMapping("/about")
	public String getAboutPage(Model model) {
		String title = "Giới thiệu";
		String page = "client_about";
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		
		return "client_main-frame";
	}

}
