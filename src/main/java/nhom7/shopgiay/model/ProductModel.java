package nhom7.shopgiay.model;

import java.text.NumberFormat;
import java.util.Date;

import nhom7.shopgiay.entity.Product;

public class ProductModel {

	private long id;
	private String name;
	private String thumbnail;
	private int price;
	private int salePrice;
	private String description;
	private Date created;
	private int realPriceUnFormat;
	
	public ProductModel() {}
	
	public ProductModel(Product p) {
		super();
		this.id = p.getId();
		this.name = p.getName();
		this.thumbnail = p.getThumbnail();
		this.price = p.getPrice();
		this.salePrice = p.getSalePrice();
		this.description = p.getDescription();
		this.created = p.getCreated();
		this.realPriceUnFormat = (int) p.getPrice() * (100 - p.getSalePrice()) / 100;
	}

	public String getRealPrice() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		return nf.format(price * (100 - salePrice) / 100);
	}
	
	public String getPriceWithFormat() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(true);
		return nf.format(price);
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
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desciption) {
		this.description = desciption;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}


	public int getRealPriceUnFormat() {
		return realPriceUnFormat;
	}

	public void setRealPriceUnFormat(int realPriceUnFormat) {
		this.realPriceUnFormat = realPriceUnFormat;
	}
	
	
	
	
}
