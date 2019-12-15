package nhom7.shopgiay.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService productService;
	
	
	@GetMapping(value = { "/", "/home" })
	public String homePage(Model model, Principal principal) {
		String page = "client_index";
		String title = "Trang chủ";
		
		//Sap xep theo thoi gian sau nhat
		Sort sort = Sort.by("created").descending();
		//Lay ra 5 ban ghi dau tien
		PageRequest pageRequest = PageRequest.of(0, 5, sort);
		
		List<Product> newestProducts;
		try {
			newestProducts = productService.getNewestProduct(pageRequest);
		} catch (Exception e) {
			newestProducts = null;
			e.printStackTrace();
		}
		
		// System.out.println(principal.getName());
		model.addAttribute("page", page);
		model.addAttribute("title", title);
		model.addAttribute("newestProducts", newestProducts);
		//System.out.println(newestProducts.size());
		return "client_main-frame";
	}

}
