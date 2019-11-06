package nhom7.shopgiay.controller.admin;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.LongStream;

import javax.servlet.http.HttpServletRequest;

import org.graalvm.compiler.nodes.cfg.HIRLoop;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.entity.ProductDetail;
import nhom7.shopgiay.model.AddProductDetailModel;
import nhom7.shopgiay.model.AddProductImageModel;
import nhom7.shopgiay.model.AddUpdateProductModel;
import nhom7.shopgiay.model.ProductDetailModel;
import nhom7.shopgiay.repository.CategoryRepository;
import nhom7.shopgiay.repository.ProductDetailRepository;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.service.PreviousPageService;
import nhom7.shopgiay.service.ProductService;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductController {

	@Autowired
	ProductRepository productRep;
	@Autowired
	ProductService productService;
	@Autowired
	ProductDetailRepository productDetailRep;
	@Autowired
	PreviousPageService prePageService;
	@Autowired
	CategoryRepository categoryRep;

	// product Controller
	@GetMapping
	public String getPage(Model model) {
		String page = "admin_products";
		String title = "Danh sách sản phẩm";
		String titleContent = "Danh sách sản phẩm";

		model.addAttribute("products", productRep.findAll());
		model.addAttribute("page", page);
		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);

		return "admin_main-frame";
	}

	@PostMapping(value = "/delete")
	public String postDeleteProduct(@RequestParam long productId, RedirectAttributes redAtt) {

		try {
			productRep.deleteById(productId);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Xóa sản phẩm thành công"));

		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}
		return "redirect:/admin/product";
	}

	@GetMapping(value = "/add")
	public String getAddProduct(Model model) {
		String page = "admin_add-product";
		String title = "Thêm sản phẩm";
		String titleContent = "Thêm mới sản phẩm";

		model.addAttribute("page", page);
		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("categories", categoryRep.findAll());

		return "admin_main-frame";
	}

	@PostMapping(value = "/add")
	public String postAddProduct(AddUpdateProductModel prd, BindingResult br, RedirectAttributes redAtt) {

		try {

			productService.addProduct(prd);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Thêm sản phẩm thành công"));

		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("name_UNIQUE")) {
				redAtt.addFlashAttribute("statusMessage", new StatusMessage(true, "ERROR: Tên sản phẩm đã tồn tại!"));
				redAtt.addFlashAttribute("product", prd);
				return "redirect:/admin/product/add";
			} else
				redAtt.addFlashAttribute("statusMessage",
						new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}

		return "redirect:/admin/product";
	}

	@GetMapping(value = "/update")
	@Transactional
	public String getUpdateProduct(@RequestParam long productId, Model model, RedirectAttributes redAtt) {
		String page = "admin_update-product";
		String title = "Update product";
		String titleContent = "Chỉnh sửa thông tin sản phẩm";

		try {
			Product product = productRep.findById(productId).get();
			// set thanh EAGER chỉ trong req này
			Hibernate.initialize(product.getCategories());
			model.addAttribute("product", product);
			model.addAttribute("categories", categoryRep.findAll());
			model.addAttribute("page", page);
			model.addAttribute("title", title);
			model.addAttribute("titleContent", titleContent);

			return "admin_main-frame";

		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}
		return "redirect:/admin/product";

	}

	@PostMapping(value = "/update")
	public String postUpdateProduct(AddUpdateProductModel updateProduct, BindingResult br, RedirectAttributes redAtt) {
		try {

			productService.updateProduct(updateProduct);

			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Sản phẩm đã được chỉnh sửa!"));
		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex.getMessage().contains("name_UNIQUE")) {
				redAtt.addFlashAttribute("statusMessage", new StatusMessage(true, "ERROR: Tên sản phẩm đã tồn tại!"));
				return "redirect:/admin/product/update?id=" + updateProduct.getId();
			} else
				redAtt.addFlashAttribute("statusMessage",
						new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}
		return "redirect:/admin/product";
	}

	// ----------------------------------------------------------------------
	// Product Image Controller

	@GetMapping(value = "/images")
	public String getProductImages(Model model, @RequestParam long productId, RedirectAttributes redAtt) {
		try {
			String title = "Quản lý hình ảnh sản phẩm";
			String titleContent = "Quản lý hình ảnh sản phẩm";
			String page = "admin_product-image";

			model.addAttribute("productImages", productService.getProductImages(productId));
			model.addAttribute("productId", productId);
			model.addAttribute("page", page);
			model.addAttribute("title", title);
			model.addAttribute("titleContent", titleContent);

			return "admin_main-frame";
		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
			return "redirect:/admin/product";
		}
	}

	@GetMapping(value = "/images/add")
	public String getAddProductImage(Model model, @RequestParam long productId, RedirectAttributes redAtt) {
		String title = "thêm mới hình ảnh";
		String titleContent = "Thêm mới hình ảnh";
		String page = "admin_add-product-image";
		Product product = null;
		try {
			product = productRep.findById(productId).get();
			model.addAttribute("page", page);
			model.addAttribute("title", title);
			model.addAttribute("titleContent", titleContent);
			model.addAttribute("product", product);

			return "admin_main-frame";
		} catch (NoSuchElementException e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không tìm thấy sản phẩm. Vui lòng thử lại!"));
		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}
		return "redirect:/admin/product";
	}

	@PostMapping(value = "/images/add")
	public String postAddProductImage(AddProductImageModel pim, RedirectAttributes redAtt) {
		try {
			productService.saveAddProductImages(pim);

			return "redirect:/admin/product/images?productId=" + pim.getProductId();
		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Dữ liệu nhận bị lỗi. Vui lòng thử lại!"));
			return "redirect:/admin/product";
		}
	}

	@GetMapping(value = "/images/delete")
	public String getDeleteProductImage(@RequestParam long id, @RequestParam long productId,
			RedirectAttributes redAtt) {
		try {
			productService.deleteProductImage(id);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Xóa hình ảnh thành công!"));
			return "redirect:/admin/product/images?productId=" + productId;
		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Thông tin bị lỗi. Vui lòng thử lại"));
			return "redirect:/admin/product/images?productId=" + productId;
		}
	}

	// ------------------------------------------------------------------------------------------------------
	// product detail controller
	@GetMapping(value = "/detail")
	public String getDetailProduct(@RequestParam long productId, Model model, RedirectAttributes redAtt) {
		String page = "admin_product-detail";
		String title = "Xem chi tiết sản phẩm";
		String titleContent = "Xem chi tiết sản phẩm";

		List<ProductDetailModel> productDetailModels;
		try {
			productDetailModels = productService.getProductDetailModel(productId);
			model.addAttribute("productDetailModels", productDetailModels);
			model.addAttribute("productId", productId);
			model.addAttribute("page", page);
			model.addAttribute("title", title);
			model.addAttribute("titleContent", titleContent);

			return "admin_main-frame";
		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(true, "Không xác định. Vui lòng thử lại!"));
		}

		return "redirect:/admin/product";
	}

	// Chỉ xem
	@GetMapping("/detail/{detailId}")
	public String getOneProductDetail(Model model, @PathVariable long detailId, RedirectAttributes redAtt,
			HttpServletRequest req) {
		String page = "admin_view-product-detail";
		String title = "Xem chi tiết sản phẩm";
		String titleContent = "Xem chi tiết sản phẩm";

		model.addAttribute("productDetail", productDetailRep.findById(detailId).get());
		model.addAttribute("page", page);
		model.addAttribute("title", title);
		model.addAttribute("titleContent", titleContent);
		model.addAttribute("prePage", prePageService.getPreviousPage(req));

		return "admin_main-frame";
	}

	@GetMapping(value = "/detail/add")
	public String getAddProductDetail(@RequestParam long productId, Model model, RedirectAttributes redAtt) {
		String page = "admin_add-product-detail";
		String title = "Thêm chi tiết sản phẩm";
		String titleContent = "Thêm chi tiết sản phẩm";
		try {
			Product product = productRep.findById(productId).get();
			model.addAttribute("page", page);
			model.addAttribute("title", title);
			model.addAttribute("titleContent", titleContent);
			model.addAttribute("product", product);

			return "admin_main-frame";

		} catch (NoSuchElementException e) {
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(true, "ERROR: Không tìm thấy sản phẩm!"));
		} catch (Exception e) {
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(true, "ERROR: Không xác định!"));
		}
		return "redirect:/admin/product";

	}

	@PostMapping(value = "/detail/add")
	public String postAddProductDetail(AddProductDetailModel productDetailAdd, BindingResult br,
			RedirectAttributes redAtt) {

		try {
			productService.saveAddProductDetail(productDetailAdd, redAtt);

		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
			return "redirect:/admin/product";
		}

		return "redirect:/admin/product/detail?productId=" + productDetailAdd.getProductId();
	}

	// xoa 1 chi tiết san pham
	@PostMapping(value = "/detail/delete")
	public String postDeleteProductDetail(RedirectAttributes redAtt, @RequestParam long productId,
			@RequestParam long id) {

		try {

			productDetailRep.deleteById(id);
			System.out.println("Xoa thanh cong product detail");
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Xóa thành công!"));
		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Xóa không thành công. Vui lòng thử lại!"));
		}

		return "redirect:/admin/product/detail?productId=" + productId;
	}

	// xoa tat ca chi tiet san pham
	@PostMapping(value = "/detail/clear")
	public String postClearProductDetail(@RequestParam long productId, RedirectAttributes redAtt) {
		try {
			productService.clearProductDetails(productId);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Xóa tất cả thành công!"));
		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}
		return "redirect:/admin/product/detail?productId=" + productId;
	}

	@GetMapping(value = "/detail/update")
	public String getUpdateProductDetail(Model model, @RequestParam long id, RedirectAttributes redAtt) {
		try {
			String title = "Sửa chi tiết sản phẩm";
			String titleContent = "Cập nhật số lượng sản phẩm";
			String page = "admin_update-product-detail";

			model.addAttribute("productDetail", productDetailRep.findById(id).get());
			model.addAttribute("title", title);
			model.addAttribute("page", page);
			model.addAttribute("titleContent", titleContent);

			return "admin_main-frame";
		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}
		return "redirect:/admin/product";
	}

	@PostMapping(value = "/detail/update")
	public String postUpdateProductDetail(HttpServletRequest req, RedirectAttributes redAtt) {

		try {

			int amount = Integer.parseInt(req.getParameter("amount"));

			ProductDetail productDetail = productService.updateProductDetailService(req.getParameter("id"), amount);
			productDetailRep.save(productDetail);
			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Cập nhật thành công!"));

			return "redirect:/admin/product/detail?productId=" + productDetail.getProduct().getId();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Lỗi định dạng số. Kiểm tra lại số lượng!"));
			return "redirect:/admin/product/detail/update?id=" + req.getParameter("id");
		} catch (Exception e) {
			e.printStackTrace();
			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Không xác định. Vui lòng thử lại!"));
		}

		return "redirect:/admin/product";

	}

}
