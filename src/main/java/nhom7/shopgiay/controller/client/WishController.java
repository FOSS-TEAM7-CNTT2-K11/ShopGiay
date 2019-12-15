package nhom7.shopgiay.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.entity.Wish;
import nhom7.shopgiay.entity.WishPK;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.repository.WishRepository;

@Controller
@RequestMapping("/wish")
public class WishController {

	@Autowired
	WishRepository wishRep;
	@Autowired
	ProductRepository productRep;

	@GetMapping("")
	public String getWishesByAccountId(HttpServletRequest req, Model model) {

		String title = "Danh sách yêu thích";
		String page = "client_wish";

		Account acc = (Account) req.getAttribute("account");
		model.addAttribute("wishes", wishRep.getsByAccountId(acc.getId()));
		model.addAttribute("title", title);
		model.addAttribute("page", page);

		return "client_main-frame";
	}

	@GetMapping("/add")
	public String getAddWish(HttpServletRequest req) {

		System.out.println("Da vao wish add controller");
		Product p = productRep.findById(Long.parseLong(req.getParameter("productId"))).get();
		Account acc = (Account) req.getAttribute("account");
		Wish w = new Wish();
		w.setAccount(acc);
		w.setProduct(p);
		w.setId(new WishPK(acc.getId(), p.getId()));
		wishRep.save(w);
		System.out.println("save wish ngon");

		return "redirect:/single-shop?productId=" + req.getParameter("productId");
	}
	
	@GetMapping("/delete")
	public String deleteWish(HttpServletRequest req, @RequestParam long productId) {
		Account acc = (Account) req.getAttribute("account");
		Product p = productRep.findById(productId).get();
		Wish w = wishRep.findById(new WishPK(acc.getId(), p.getId()) ).get();
		wishRep.delete(w);
		return "redirect:" + req.getHeader("Referer");
	}
}
