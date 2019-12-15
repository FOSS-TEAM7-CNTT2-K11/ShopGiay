package nhom7.shopgiay.model;

import java.util.Arrays;

public class FilterModel {

	private String categories;
	private int priceMin;
	private int priceMax;
	private String sizes;
	private int maxPage;

	public FilterModel(String categories, int priceMin, int priceMax, String sizes, int maxPage) {
		super();
		this.categories = categories;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.sizes = sizes;
		this.maxPage = maxPage;
	}
	
	public FilterModel() {
		
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public long[] getCategories() {
		if(categories.trim().isEmpty()) return new long[0];
		String tam[] = categories.split(",");
		long[] array = Arrays.asList(tam).stream().mapToLong(Long::parseLong).toArray();
		return array;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public int getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}

	public int getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}

	public long[] getSizes() {
		if(sizes.trim().isEmpty()) {
			return new long[0] ;
		}
		String tam[] = sizes.split(",");
		long[] array = Arrays.asList(tam).stream().mapToLong(Long::parseLong).toArray();
		return array;
	}

	public void setSizes(String  sizes) {
		this.sizes = sizes;
	}

}
