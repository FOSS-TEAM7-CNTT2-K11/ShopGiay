package nhom7.shopgiay.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.repository.CartItemRepository;
import nhom7.shopgiay.repository.CheckoutRepository;
import nhom7.shopgiay.service.AccountService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	AccountService accService;
	@Autowired
	CheckoutRepository checkoutRep;
	@Autowired
	CartItemRepository cartItemRep;
	
	@GetMapping()
	public String getUserPage(Model model, HttpServletRequest req) {
		Account account = (Account) req.getAttribute("account");
		
		String title = "Thông tin khách hàng";
		String page = "client_user";
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		
		try {
			model.addAttribute("accModel", accService.getAccountModel(account.getId()));
		} catch (Exception e) {
			model.addAttribute("statusMessage", new StatusMessage(true, "Không thể tìm thông tin khách hàng. Vui lòng kiểm tra lại!"));
		}
		
		return "client_main-frame";
	}

	@GetMapping("/view-history-bill")
	public String getHistoryUser(Model model, HttpServletRequest req) {
Account account = (Account) req.getAttribute("account");
		
		String title = "Lịch sử mua hàng";
		String page = "client_user-history";
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.getByAccountId(account.getId()));

		
		return "client_main-frame";
	}
	

	@GetMapping("/view-history-bill/detail")
	public String getHistoryUserDetail(Model model, HttpServletRequest req, @RequestParam long checkoutId) {
Account account = (Account) req.getAttribute("account");
		
		String title = "Lịch sử mua hàng";
		String page = "client_user-history-detail";
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		model.addAttribute("checkoutDetail", cartItemRep.getsByCheckoutId(checkoutId));

		
		return "client_main-frame";
	}
}
