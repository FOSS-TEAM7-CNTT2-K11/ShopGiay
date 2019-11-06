package nhom7.shopgiay.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.repository.AccountRepository;
import nhom7.shopgiay.repository.CheckoutRepository;
import nhom7.shopgiay.service.AccountService;
import nhom7.shopgiay.service.PreviousPageService;

@Controller
public class ViewUserController {

	@Autowired
	PreviousPageService prePageService;
	@Autowired
	AccountRepository accRep;
	@Autowired
	AccountService accService;
	@Autowired
	CheckoutRepository checkoutRep;
	
	@GetMapping("/admin/view-user")
	public String getViewUser(Model model, @RequestParam long accId, HttpServletRequest req) {
		String title = "Thông tin khách hàng";
		String titleContent = "Thông tin khách hàng";
		String page = "admin_view-user";
		
		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		try {
			model.addAttribute("accModel", accService.getAccountModel(accId));
		} catch (Exception e) {
			model.addAttribute("statusMessage", new StatusMessage(true, "Không thể tìm thông tin khách hàng. Vui lòng kiểm tra lại!"));
		}
		model.addAttribute("prePage", prePageService.getPreviousPage(req));
		
		return "admin_main-frame";
				
	}
	
	
	@GetMapping("/admin/view-history-user")
	public String getHistoryUser(Model model, @RequestParam long accountId, HttpServletRequest req) {
		String title = "Thông tin khách hàng";
		String titleContent = "Lịch sử mua hàng";
		String page = "admin_checkout";
		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.getByAccountId(accountId));
		model.addAttribute("typePage", "all");
		model.addAttribute("prePage", prePageService.getPreviousPage(req));
		return "admin_main-frame";
	}
}
