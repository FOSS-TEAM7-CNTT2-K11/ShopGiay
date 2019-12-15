package nhom7.shopgiay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nhom7.shopgiay.entity.Product;
import nhom7.shopgiay.entity.ProductDetail;
import nhom7.shopgiay.model.SizeColorModel;
import nhom7.shopgiay.repository.ColorRepository;
import nhom7.shopgiay.repository.CommentRepository;
import nhom7.shopgiay.repository.SizeRepository;

@Service
public class SingleShopService {

	@Autowired
	ProductService productService;
	@Autowired 
	ColorRepository colorRep;
	@Autowired
	SizeRepository sizeRep;
	@Autowired
	CommentRepository commentRep;
	
	
	private boolean checkSizeExists(String size, List<SizeColorModel> scms) {
		for (SizeColorModel sizeColorModel : scms) {
			if (sizeColorModel.getSize().equalsIgnoreCase(size))
				return true;
		}
		return false;
	}

	public List<SizeColorModel> getColorBySize(Product product) {
		List<SizeColorModel> scms = new ArrayList<SizeColorModel>();
		SizeColorModel scm;

		for (ProductDetail pd : product.getProductDetails()) {
			if (!checkSizeExists(pd.getSize().getSize(), scms)) {
				scm = new SizeColorModel();
				scm.setSize(pd.getSize().getSize());
				scms.add(scm);
			}
		}

		for (SizeColorModel sizeColorModel : scms) {

			for (ProductDetail pd : product.getProductDetails()) {
				if (pd.getSize().getSize().equalsIgnoreCase(sizeColorModel.getSize())) {
					sizeColorModel.getSizeHaveColor().add(pd.getColor().getColor());
				}
			}
		}

		
		return scms;
	}
	
	
}
