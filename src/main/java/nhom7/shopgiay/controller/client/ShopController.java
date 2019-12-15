package nhom7.shopgiay.controller.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.model.FilterModel;
import nhom7.shopgiay.model.ProductModel;
import nhom7.shopgiay.repository.CategoryRepository;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.repository.SizeRepository;
import nhom7.shopgiay.service.FilterService;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	@Autowired
	FilterService fillterService;
	@Autowired
	ProductRepository productRep;

	@Autowired
	CategoryRepository catRep;
	@Autowired
	SizeRepository sizeRep;

	@GetMapping()
	public String getShopPage(Model model) {
		String title = "shop";
		String page = "client_shop";

		model.addAttribute("categories", catRep.findAll());
		model.addAttribute("sizes", sizeRep.findAll());

		model.addAttribute("title", title);
		model.addAttribute("page", page);
		return "client_main-frame";
	}

	@PostMapping("/{pageNumber}")
	public ResponseEntity<Object> getShopPagePageable(@PathVariable("pageNumber") int pageNumber) {
		System.out.println("Da vao getshoppagepageable");

		List<Product> products = productRep.abc(PageRequest.of(pageNumber, 12));
		List<ProductModel> pms = new ArrayList<ProductModel>();
		ProductModel pm;
		for (Product p : products) {
			pm = new ProductModel();
			pm.setId(p.getId());
			pm.setCreated(p.getCreated());
			pm.setPrice(p.getPrice());
			pm.setSalePrice(p.getSalePrice());
			pm.setDescription(p.getDescription());
			pm.setThumbnail(p.getThumbnail());
			pm.setName(p.getName());
			pms.add(pm);

		}
		System.out.println("Products size: " + pms.size());
		return new ResponseEntity<Object>(pms, HttpStatus.OK);
	}

	@GetMapping(value = "/fillter")
	public ResponseEntity<Object> test(HttpServletRequest req) {
		FilterModel fm = new FilterModel();
		fm.setCategories(req.getParameter("categories"));
		fm.setSizes(req.getParameter("sizes"));
		int page = Integer.parseInt(req.getParameter("page"));
		fm.setPriceMax(Integer.parseInt(req.getParameter("priceMax")));
		fm.setPriceMin(Integer.parseInt(req.getParameter("priceMin")));
		
		List<Product> products = fillterService.filter(fm);
		List<ProductModel> pms = new ArrayList<ProductModel>();
		for (Product p : products) {
			pms.add(new ProductModel(p));
		}
		PagedListHolder<ProductModel> pageList = new PagedListHolder<ProductModel>();
		pageList.setSource(pms);
		pageList.setPage(page);
		pageList.setPageSize(12);

		for (ProductModel pd : pms) {
			System.out.println(pd.getName());
		}
		return ResponseEntity.ok().body(pageList);
	}

}
