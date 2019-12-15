package nhom7.shopgiay.model;

import java.util.ArrayList;
import java.util.List;

public class SizeColorModel {

	private String size;
	private List<String> sizeHaveColor;
	
	public SizeColorModel() {
		sizeHaveColor = new ArrayList<String>();
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<String> getSizeHaveColor() {
		return sizeHaveColor;
	}

	public void setSizeHaveColor(List<String> sizeHaveColor) {
		this.sizeHaveColor = sizeHaveColor;
	}
	
}
