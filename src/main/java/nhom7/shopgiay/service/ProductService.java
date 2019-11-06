package nhom7.shopgiay.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nhom7.shopgiay.custom.MyException;
import nhom7.shopgiay.custom.StatusMessage;
import nhom7.shopgiay.entity.CartItem;
import nhom7.shopgiay.entity.Category;
import nhom7.shopgiay.entity.Color;
import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.entity.ProductDetail;
import nhom7.shopgiay.entity.ProductImage;
import nhom7.shopgiay.entity.Size;
import nhom7.shopgiay.model.AddProductDetailModel;
import nhom7.shopgiay.model.AddProductImageModel;
import nhom7.shopgiay.model.AddUpdateProductModel;
import nhom7.shopgiay.model.ProductDetailModel;
import nhom7.shopgiay.repository.CartItemRepository;
import nhom7.shopgiay.repository.CategoryRepository;
import nhom7.shopgiay.repository.ColorRepository;
import nhom7.shopgiay.repository.ProductDetailRepository;
import nhom7.shopgiay.repository.ProductImageRepository;
import nhom7.shopgiay.repository.ProductRepository;
import nhom7.shopgiay.repository.SizeRepository;

@Service
public class ProductService {

	@Autowired
	ProductDetailRepository productDetailRep;
	@Autowired
	ProductRepository productRep;
	@Autowired
	ColorRepository colorRep;
	@Autowired
	SizeRepository sizeRep;
	@Autowired
	ProductImageRepository productImageRep;
	@Autowired
	UploadFileService uploadService;
	@Autowired
	CartItemRepository cartItemRep;
	@Autowired
	CategoryRepository categoryRep;

	private List<ProductDetailModel> productDetailModelList;

	public String getUploadFileFolder() {
		String path = System.getProperty("user.home");

		return path.replace('\\', '/').concat("/shopgiay/images/");
	}

	public List<Category> getCategoriesByIds(long[] catIds) throws Exception {
		Category cat;
		List<Category> catList = new ArrayList<Category>();
		for (long catId : catIds) {
			cat = new Category();
			cat = categoryRep.findById(catId).get();
			catList.add(cat);
		}
		return catList;
	}

	public Product addProduct(AddUpdateProductModel productAdd) throws Exception {
		Product p = new Product();
		if (!productAdd.getThumbnail().isEmpty()) {
			String thumbnail = uploadService.upload(productAdd.getThumbnail());
			p.setThumbnail(thumbnail);
		}
		p.setName(productAdd.getName());
		p.setCreated(new Date(System.currentTimeMillis()));
		p.setPrice(productAdd.getPrice());
		p.setSalePrice(productAdd.getSalePrice());
		p.setDescription(productAdd.getDescription());
		p.setCategories(getCategoriesByIds(productAdd.getCategoryId()));

		return productRep.save(p);
	}

	public Product updateProduct(AddUpdateProductModel productUpdate) throws Exception {
		Product p;
		
		p = productRep.findById(productUpdate.getId()).get();
		

		if (!productUpdate.getThumbnail().isEmpty()) {
			String thumbnail = uploadService.upload(productUpdate.getThumbnail());
			p.setThumbnail(thumbnail);
		}
		p.setName(productUpdate.getName());

		p.setPrice(productUpdate.getPrice());
		p.setSalePrice(productUpdate.getSalePrice());
		p.setDescription(productUpdate.getDescription());
		p.setCategories(getCategoriesByIds(productUpdate.getCategoryId()));
		return productRep.save(p);
	}

	// -------------------------------------------------------------

	public List<ProductImage> getProductImages(long productId) throws Exception {
		List<ProductImage> productImages = productImageRep.getByProductId(productId);
		return productImages;
	}

	public void saveAddProductImages(AddProductImageModel pim) throws Exception {
		System.out.println("p id: " + pim.getProductId());
		Product product = productRep.findById(pim.getProductId()).get();
		ProductImage productImage;

		for (MultipartFile mpf : pim.getImage()) {
			productImage = new ProductImage();
			productImage.setProduct(product);
			String imageName = uploadService.upload(mpf);
			productImage.setImage(imageName);
			productImageRep.save(productImage);
		}
	}

	public void deleteProductImage(long id) throws Exception {
		productImageRep.deleteById(id);
	}

	public void clearProductImage(long productId) throws Exception {
		productImageRep.clearByProdudctId(productId);
	}

	// ----------------------------------

	public List<ProductDetail> getProductDetailsByProductId(long idProduct) throws Exception {
		return productDetailRep.getByProductId(idProduct);
	}

	public List<ProductDetailModel> getProductDetailModel(long idProduct) throws Exception {
		ProductDetailModel pdm;
		productDetailModelList = new ArrayList<ProductDetailModel>();

		// Lay ra tat ca san pham chi tiet cua san pham co id = idproduct
		List<ProductDetail> productDetailList = this.getProductDetailsByProductId(idProduct);
		System.out.println(productDetailList.size());
		String imagePath = null;

		for (ProductDetail productDetail : productDetailList) {

			pdm = new ProductDetailModel();
			pdm.setId(productDetail.getId());
			pdm.setAmount(productDetail.getAmount());
			pdm.setColor(productDetail.getColor());
			pdm.setSize(productDetail.getSize());
			pdm.setProduct(productDetail.getProduct());
			productDetailModelList.add(pdm);
		}

		return productDetailModelList;
	}

	// return ProductDetail nếu đã tồn tại cả color và size và product.
	// ngược lại trả về null
	public ProductDetail checkExistsProductDetail(long productId, String color, String size) {

		List<ProductDetail> productDetails = productDetailRep.getByProductId(productId);

		for (ProductDetail productDetail : productDetails) {
			if (productDetail.getColor().getColor().equalsIgnoreCase(color)
					&& productDetail.getSize().getSize().equalsIgnoreCase(size)) {
				return productDetail;
			}

		}

		return null;
	}

	// xử lý việc thêm product detail
	public void saveAddProductDetail(AddProductDetailModel productDetailAdd, RedirectAttributes redAtt)
			throws Exception {

		long productId = productDetailAdd.getProductId();
		String colorInput = productDetailAdd.getColor().trim();
		String sizeInput = productDetailAdd.getSize().trim();
		int amountInput = productDetailAdd.getAmount();

		Product product = productRep.findById(productId).get();
		ProductDetail productDetail = checkExistsProductDetail(productId, colorInput, sizeInput);

		if (productDetail == null) {
			// Tao moi color va size.
			Color color = new Color();
			color.setColor(colorInput);
			Size size = new Size();
			size.setSize(sizeInput);

			// Lưu lại color và size. Neu da ton tai thi bỏ lưu và lấy ra!
			try {
				color = colorRep.save(color);
			} catch (Exception e) {
				if (e.getMessage().contains("UNIQUE"))
					color = colorRep.findByColor(colorInput);
				else
					throw new Exception();
			}
			try {
				size = sizeRep.save(size);

			} catch (Exception e) {
				if (e.getMessage().contains("UNIQUE"))
					size = sizeRep.findBySize(sizeInput);
				else
					throw new Exception();
			}

			// Lưu dữ liệu vào ProductDetail. Gồm: color, size, product, số lượng.
			productDetail = new ProductDetail();
			productDetail.setColor(color);
			productDetail.setSize(size);
			productDetail.setProduct(product);
			productDetail.setAmount(amountInput);

			productDetailRep.save(productDetail);

			redAtt.addFlashAttribute("statusMessage", new StatusMessage(false, "Thêm chi tiết sản phẩm thành công"));
		} else {

			redAtt.addFlashAttribute("statusMessage",
					new StatusMessage(true, "ERROR: Chi tiết sản phẩm đã tồn tại. Bạn có thể câp nhật lại số lượng!"));
		}

	}

	// update số lượng cho chi tiết sản phẩm
	public ProductDetail updateProductDetailService(String idInput, int amount) throws MyException, Exception {
		long id = Long.parseLong(idInput);
		ProductDetail pd = productDetailRep.findById(id).get();
		pd.setAmount(amount);
		return pd;
	}

	public boolean updateAmountAfterConfirm(long checkoutId) throws Exception {
		ProductDetail productDetail;

		List<CartItem> waitingItems = cartItemRep.getWaitingItemsByCheckoutId(checkoutId);
		for (CartItem cartItem : waitingItems) {
			productDetail = productDetailRep.findById(cartItem.getProductDetail().getId()).get();
			productDetail.setAmount(productDetail.getAmount() - cartItem.getAmount());
			if (productDetail.getAmount() < 0)
				throw new MyException("Số lượng sản phẩm không đủ. Không thể xác nhận đơn hàng.");
			productDetailRep.save(productDetail);

		}

		return true;
	}

	public boolean updateAmountAfterDeny(long checkoutId) throws Exception {
		ProductDetail productDetail;

		List<CartItem> waitingItems = cartItemRep.getWaitingItemsByCheckoutId(checkoutId);
		for (CartItem cartItem : waitingItems) {
			productDetail = productDetailRep.findById(cartItem.getProductDetail().getId()).get();
			productDetail.setAmount(productDetail.getAmount() + cartItem.getAmount());
			productDetailRep.save(productDetail);

		}

		return true;
	}

}
