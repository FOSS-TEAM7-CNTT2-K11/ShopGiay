package nhom7.shopgiay.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nhom7.shopgiay.custom.CustomUserDetails;
import nhom7.shopgiay.service.CartService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	public CartService cartService;

	@GetMapping
	public String cartPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetail = (CustomUserDetails) auth;
		model.addAttribute("cartItems", cartService.getListCartByUserId(userDetail.getAccount().getId()));
		return "cart";
	}
}
