package nhom7.shopgiay.model;

import org.springframework.web.multipart.MultipartFile;

public class AddUpdateProductModel {

	private long id;
	private long[] categoryId;
	private String name;
	private int price;
	private int salePrice;
	private MultipartFile thumbnail;
	private String description;

	
	public long[] getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long[] categoryId) {
		this.categoryId = categoryId;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int sale) {
		this.salePrice = sale;
	}

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
