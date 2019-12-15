package nhom7.shopgiay.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nhom7.shopgiay.entity.Account;
import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.model.CommentModel;
import nhom7.shopgiay.repository.ProductImageRepository;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.service.ProductService;
import nhom7.shopgiay.service.SingleShopService;

@Controller
@RequestMapping("/single-shop")
public class SingleShopController {

	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRep;
	@Autowired
	SingleShopService singleShopService;
	@Autowired
	ProductImageRepository productImageRep;

	@GetMapping()
	public String getProductPage(Model model, @RequestParam long productId, HttpServletRequest req) {
		String title = "Chi tiết sản phẩm";
		String page = "client_single-shop";
		Account acc = (Account) req.getAttribute("account");
		// Lấy thông tin sản phẩm đang trỏ
		Product product = null;
		try {
			product = productRep.findById(productId).get();
			product.setProductImages(productImageRep.getByProductId(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		// code này lấy ra 5 sản phẩm mới nhất
		// Sap xep theo thoi gian sau nhat
		Sort sort = Sort.by("created").descending();
		// Lay ra 5 ban ghi dau tien
		PageRequest pageRequest = PageRequest.of(0, 5, sort);

		List<Product> newestProducts;
		try {
			newestProducts = productService.getNewestProduct(pageRequest);
		} catch (Exception e) {
			newestProducts = null;
			e.printStackTrace();
		}
		// kết thúc lấy sản phẩm mới nhất
		
		
		// Xử lý trước size color cho sản phẩm
		model.addAttribute("sizeColors", singleShopService.getColorBySize(product));
		
		model.addAttribute("title", title);
		model.addAttribute("page", page);
		model.addAttribute("newestProducts", newestProducts);
		model.addAttribute("product", product);
		model.addAttribute("account", acc);
		return "client_main-frame";
	}
	

}
