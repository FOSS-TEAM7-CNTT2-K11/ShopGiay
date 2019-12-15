package nhom7.shopgiay.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@GetMapping("")
	public String getRegisterPage(Model model) {
		String title = "Đăng ký tài khoản";
		String page = "client_register";
		
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		
		return "client_main-frame";
	}
}
