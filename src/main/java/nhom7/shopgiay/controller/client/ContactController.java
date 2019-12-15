package nhom7.shopgiay.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

	@GetMapping("/contact")
	public String getContactPage(Model model ) {
		String title = "Liên hệ";
		String page = "client_contact";
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		
		return "client_main-frame";
	}
}
