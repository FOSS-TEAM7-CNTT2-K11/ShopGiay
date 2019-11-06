package nhom7.shopgiay.model;

import org.springframework.web.multipart.MultipartFile;

public class AddProductImageModel {
	private long productId;
	private MultipartFile[] image;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public MultipartFile[] getImage() {
		return image;
	}
	public void setImage(MultipartFile[] image) {
		this.image = image;
	}
	
	
}
