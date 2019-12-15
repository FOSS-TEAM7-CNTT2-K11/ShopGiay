package nhom7.shopgiay.controller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.CartItem;
import nhom7.shopgiay.entity.Checkout;
import nhom7.shopgiay.model.CartItemModel;
import nhom7.shopgiay.repository.AccountRepository;
import nhom7.shopgiay.repository.CartItemRepository;
import nhom7.shopgiay.repository.CheckoutRepository;
import nhom7.shopgiay.service.CartService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	public CartService cartService;
	@Autowired
	AccountRepository accountRep;
	@Autowired
	CheckoutRepository checkoutRep;
	@Autowired
	CartItemRepository cartItemRep;

	@GetMapping
	public String cartPage(Model model) {

		String title = "Giỏ hàng";
		String page = "client_cart";

		model.addAttribute("title", title);
		model.addAttribute("page", page);
		return "client_main-frame";
	}

	@PostMapping("/add-to-cart")
	public String postAddProductToCart(HttpServletRequest req, @RequestParam long productId, @RequestParam String size,
			@RequestParam String color, @RequestParam int amout, RedirectAttributes redAtt) {
		System.out.println("Da vao add to cart");
		Account acc = (Account) req.getAttribute("account");
		System.out.println(acc.getUsername());

		if (cartService.checkInput(size, color, amout) && cartService.addToCart(productId, size, color, amout, acc)) {
			redAtt.addFlashAttribute("message", new StatusMessage(false, "Thêm vào giỏ hàng thành công!"));
		} else {

			redAtt.addFlashAttribute("message", new StatusMessage(true, "Vui lòng kiểm tra lại dữ liệu!!"));
		}

		redAtt.addFlashAttribute("productId", productId);
		return "redirect:/single-shop?productId=" + productId;
	}

	@GetMapping("/update")
	public ResponseEntity<Object> getUpdateCart(@RequestParam long id, @RequestParam int amount,
			HttpServletRequest req) {
		CartItem c = cartItemRep.findById(id).get();
		c.setAmount(amount);
		cartItemRep.save(c);
		return new ResponseEntity<Object>(new ArrayList<String>().add("ok"), HttpStatus.OK);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<Object> deleteCart(@RequestParam long id){
		cartItemRep.deleteById(id);
		return new ResponseEntity<Object>(new ArrayList<String>().add("ok"), HttpStatus.OK);
	}

	@GetMapping("/get-carts")
	public ResponseEntity<Object> getCarts(HttpServletRequest req) {

		List<CartItem> cartItems = (List<CartItem>) req.getAttribute("cartItems");

		List<CartItemModel> cartItemModels = new ArrayList<CartItemModel>();
		for (CartItem cartItem : cartItems) {
			
			CartItemModel cm = new CartItemModel(cartItem);
			
			cartItemModels.add(cm);
		}
		System.out.println(cartItemModels.size());
		return new ResponseEntity<Object>(cartItemModels, HttpStatus.OK);

	}
	
	@GetMapping("/success-pay")
	public String getSuccessPay(Model model) {
		String title = "Thanh toán thành công";
		String page = "client_thankyou";

		model.addAttribute("title", title);
		model.addAttribute("page", page);
		return "client_main-frame";
	}

	@GetMapping("/checkout")
	public String getCheckout(HttpServletRequest req, RedirectAttributes redAtt) {
		Account acc = (Account) req.getAttribute("account");
		List<CartItem> cartItems = (List<CartItem>) req.getAttribute("cartItems");
		if(cartItems.size() == 0) {
			redAtt.addFlashAttribute("message", new StatusMessage(true, "Giỏ hàng trống!"));
			return "redirect:/cart/";
		}
		Checkout ck = new Checkout();
		ck.setAccount(acc);
		ck.setCreated(new Date());
		ck = checkoutRep.save(ck);
		
		for (CartItem cartItem : cartItems) {
			cartItem.setCheckout(ck);
			cartItemRep.save(cartItem);
		}
		return "redirect:/cart/success-pay";
	}
}
