package nhom7.shopgiay.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nhom7.shopgiay.custom.MyException;
import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.Checkout;
import nhom7.shopgiay.repository.CartItemRepository;
import nhom7.shopgiay.repository.CheckoutRepository;
import nhom7.shopgiay.service.PreviousPageService;
import nhom7.shopgiay.service.ProductService;

@Controller
@RequestMapping(value = "/admin/checkout")
public class CheckoutController {

	@Autowired
	CheckoutRepository checkoutRep;
	@Autowired
	CartItemRepository cartItemRep;
	@Autowired
	PreviousPageService prePageService;
	@Autowired
	ProductService productService;
	
	@GetMapping
	public String getAllCheckouts(Model model) {
		String title = "Danh sách đơn hàng";
		String titleContent = "Danh sách tất cả đơn hàng";
		String page = "admin_checkout";

		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.findAll());
		model.addAttribute("typePage", "all");
		return "admin_main-frame";
	}

	@GetMapping("/detail")
	public String getDetailCheckout(Model model, @RequestParam long checkoutId, HttpServletRequest req) {
		String title = "Chi tiết đơn hàng";
		String titleContent = "Chi tiết đơn hàng";
		String page = "admin_checkout-detail";

		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkoutDetail", cartItemRep.getWaitingItemsByCheckoutId(checkoutId));
		model.addAttribute("prePage", prePageService.getPreviousPage(req));
		return "admin_main-frame";
	}

	@GetMapping("/confirm")
	public String getConfirmCheckout(Model model, @RequestParam long checkoutId, RedirectAttributes redAtt,
			@RequestParam String typePage) {
		try {
			Checkout checkout = checkoutRep.findById(checkoutId).get();
			productService.updateAmountAfterConfirm(checkoutId);
			checkout.setConfirm(true);
			
			checkoutRep.save(checkout);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Xác nhận thành công đơn hàng!"));

		}catch (MyException ex) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, ex.getMessage()));
		}
		catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Gặp lỗi khi xác nhận. Vui lòng thử lại!"));
		}
		
		//Trả về trang trước
		if (typePage.equals("waiting"))
			return "redirect:/admin/checkout/waiting-checkout";
		else
			return "redirect:/admin/checkout";
	}

	@GetMapping("/complete")
	public String getCompleteCheckout(Model model, @RequestParam long checkoutId, RedirectAttributes redAtt, @RequestParam String typePage) {
		try {
			Checkout checkout = checkoutRep.findById(checkoutId).get();
			checkout.setStatus(1);
			checkoutRep.save(checkout);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Đơn hàng đã hoàn thành!"));

		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Gặp lỗi khi xác nhận hoàn thành. Vui lòng thử lại!"));
		}
		
		if (typePage.equals("completing"))
			return "redirect:/admin/checkout/completing-checkout";
		else
			return "redirect:/admin/checkout";
	}
	
	@GetMapping("/deny")
	public String getDenyCheckout(Model model, @RequestParam long checkoutId, RedirectAttributes redAtt, @RequestParam String typePage) {
		try {
			Checkout checkout = checkoutRep.findById(checkoutId).get();
			checkout.setStatus(2);
			productService.updateAmountAfterDeny(checkoutId);
			checkoutRep.save(checkout);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Từ chối đơn hàng thành công!"));

		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Gặp lỗi khi từ chối đơn hàng. Vui lòng thử lại!"));
		}
		if (typePage.equals("completing"))
			return "redirect:/admin/checkout/completing-checkout";
		else
			return "redirect:/admin/checkout";
	}

	@GetMapping("/waiting-checkout")
	public String getWaitingCheckout(Model model) {
		System.out.println("wting ch	");
		String title = "Danh sách đơn hàng";
		String titleContent = "Danh sách đơn hàng đang chờ";
		String page = "admin_checkout";

		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.getWaitingCheckouts());
		model.addAttribute("typePage", "waiting");
		return "admin_main-frame";
	}

	@GetMapping("/completing-checkout")
	public String getCompletingCheckout(Model model) {
		String title = "Danh sách đơn hàng";
		String titleContent = "Danh sách đơn hàng đang giao";
		String page = "admin_checkout";

		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.getCompletingCheckouts());
		model.addAttribute("typePage", "completing");
		return "admin_main-frame";
	}

	@GetMapping("/completed-checkout")
	public String getCompletedCheckout(Model model) {
		String title = "Danh sách đơn hàng";
		String titleContent = "Danh sách đơn hàng đã giao";
		String page = "admin_checkout";

		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.getCompletedCheckouts());
		model.addAttribute("typePage", "completing");
		return "admin_main-frame";
	}
	
	@GetMapping("/denyed-checkout")
	public String getDenyingCheckout(Model model) {
		String title = "Danh sách đơn hàng";
		String titleContent = "Danh sách đơn hàng bị hủy";
		String page = "admin_checkout";

		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("page", page);
		model.addAttribute("checkouts", checkoutRep.getDenyedCheckouts());
		model.addAttribute("typePage", "completing");
		return "admin_main-frame";
	}

}
