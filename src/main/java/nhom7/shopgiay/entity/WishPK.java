package nhom7.shopgiay.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class WishPK implements Serializable{

	@GeneratedValue
	private long account_id;
	@GeneratedValue
	private long product_id;
	
	public WishPK() {}
	
	public WishPK(long account_id, long product_id) {
		super();
		this.account_id = account_id;
		this.product_id = product_id;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	
	@Override
	public boolean equals(Object obj) {
		WishPK w = (WishPK) obj;
		return w.account_id == this.account_id && w.product_id == this.product_id;
	}
	
	
}
